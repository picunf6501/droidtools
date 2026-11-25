package com.droidbubble.app.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.Executors

object ShizukuHelper {

    private const val TAG = "ShizukuHelper"
    const val SHIZUKU_PERMISSION_REQUEST_CODE = 4001
    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    private const val ICON_BLACKLIST_FULL = "no_calling,call_strength,alarm_clock,rotate,headset,data_saver,ime,sync_failing,sync_active,nfc,tty,speakerphone,cdma_eri,data_connection,phone_evdo_signal,phone_signal,secure,managed_profile,connected_display,vpn,bluetooth,nothing_earphone,camera,microphone,location,status_bar_mute,volume,zen,screen_record,cast,network_speed,ethernet,satellite,wifi,hotspot,vowifi,volte,vonr,stacked_mobile,mobile,airplane,battery,sensors_off,cell,ims_volte,wifi_calling,battery_saver,power_saver,clock,time,mute,vibrate,bt,bluetooth_connected,bluetooth_battery,bt_battery,bt_connected,hearing_aid,bluetooth_hearing_aid,headphone,earphone,earphones,alarm,dnd,do_not_disturb,nfc_on,gps,eye_protect,notification,notifications,notification_icons,notification_badge,notification_badges,badges,more_notifications,app_badge,app_icon,new_message,message,mail,sms"

    fun isShizukuInstalled(context: Context): Boolean {
        return try {
            context.packageManager.getPackageInfo("moe.shizuku.privileged.api", 0)
            true
        } catch (_: Throwable) {
            false
        }
    }

    fun isShizukuAvailable(): Boolean {
        return try {
            Shizuku.pingBinder()
        } catch (_: Throwable) {
            false
        }
    }

    fun hasShizukuPermission(): Boolean {
        if (!isShizukuAvailable()) return false
        return try {
            if (Shizuku.isPreV11()) false
            else Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
        } catch (_: Throwable) {
            false
        }
    }

    fun hasWriteSecureSettings(context: Context): Boolean {
        return context.checkSelfPermission(Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED
    }

    fun requestShizukuPermission(): Boolean {
        return try {
            if (isShizukuAvailable() && !hasShizukuPermission()) {
                Shizuku.requestPermission(SHIZUKU_PERMISSION_REQUEST_CODE)
                true
            } else {
                false
            }
        } catch (e: Throwable) {
            Log.e(TAG, "Failed to request Shizuku permission", e)
            false
        }
    }

    fun grantWriteSecureSettings(packageName: String, onResult: (Boolean) -> Unit) {
        if (!isShizukuAvailable() || !hasShizukuPermission()) {
            onResult(false)
            return
        }
        executor.execute {
            val success = runShellCommand("pm grant $packageName android.permission.WRITE_SECURE_SETTINGS")
            mainHandler.post { onResult(success) }
        }
    }

    fun executeAsync(command: String, onResult: ((Boolean) -> Unit)? = null) {
        executor.execute {
            val success = executeSync(command)
            if (onResult != null) {
                mainHandler.post { onResult(success) }
            }
        }
    }

    fun executeSync(command: String): Boolean {
        if (isShizukuAvailable() && hasShizukuPermission()) {
            if (runShellCommand(command)) return true
        }
        return try {
            val p = Runtime.getRuntime().exec(arrayOf("su", "-c", command))
            p.waitFor() == 0
        } catch (_: Throwable) {
            false
        }
    }

    fun executeWithOutputSync(command: String): String? {
        if (isShizukuAvailable() && hasShizukuPermission()) {
            try {
                val method = Shizuku::class.java.getDeclaredMethod(
                    "newProcess",
                    Array<String>::class.java,
                    Array<String>::class.java,
                    String::class.java
                )
                method.isAccessible = true
                val process = method.invoke(null, arrayOf("sh", "-c", command), null, null) as java.lang.Process
                val output = BufferedReader(InputStreamReader(process.inputStream)).readText()
                process.waitFor()
                return output.trim()
            } catch (e: Throwable) {
                Log.e(TAG, "Error reading shell output via Shizuku: $command", e)
            }
        }
        return try {
            val p = Runtime.getRuntime().exec(arrayOf("su", "-c", command))
            val output = BufferedReader(InputStreamReader(p.inputStream)).readText()
            p.waitFor()
            output.trim()
        } catch (_: Throwable) {
            null
        }
    }

    private const val PREFS_NAME = "shizuku_statusbar_backup_prefs"
    private const val KEY_SAVED_ICON_BLACKLIST = "saved_icon_blacklist"
    private const val KEY_HAD_CUSTOM_BLACKLIST = "had_custom_blacklist"
    private const val KEY_SAVED_POLICY_CONTROL = "saved_policy_control"
    private const val KEY_SAVED_BATTERY_PERCENT = "saved_battery_percent"
    private const val KEY_STATUS_BAR_HIDDEN_STATE = "status_bar_hidden_state"

    fun isSystemStatusBarIconsHidden(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if (prefs.contains(KEY_STATUS_BAR_HIDDEN_STATE)) {
            return prefs.getBoolean(KEY_STATUS_BAR_HIDDEN_STATE, false)
        }
        return try {
            val bl = Settings.Secure.getString(context.contentResolver, "icon_blacklist") ?: ""
            bl.contains("clock") && bl.contains("battery") && bl.contains("wifi")
        } catch (_: Throwable) {
            false
        }
    }

    fun setSystemStatusBarIconsHidden(context: Context, hidden: Boolean, onResult: ((Boolean) -> Unit)? = null) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val wasAlreadyHidden = prefs.getBoolean(KEY_STATUS_BAR_HIDDEN_STATE, false)
        prefs.edit().putBoolean(KEY_STATUS_BAR_HIDDEN_STATE, hidden).apply()

        executor.execute {
            var success = false

            // Dynamically fetch any device-specific status bar icon slot names from SystemUI
            val dynamicSlots = if (hidden) {
                val rawSlots = executeWithOutputSync("cmd statusbar get-status-icons") ?: ""
                val regex = Regex("\\(([^)]+)\\)")
                regex.findAll(rawSlots).map { it.groupValues[1].trim() }.filter { it.isNotEmpty() }.toList()
            } else {
                emptyList()
            }

            val fullBlacklistString = if (dynamicSlots.isNotEmpty()) {
                (ICON_BLACKLIST_FULL.split(",") + dynamicSlots).map { it.trim() }.filter { it.isNotEmpty() }.distinct().joinToString(",")
            } else {
                ICON_BLACKLIST_FULL
            }

            if (hidden && !wasAlreadyHidden) {
                // Snapshot existing icon_blacklist, policy_control, and battery percent BEFORE overwriting
                // so we only restore the icons that were actually used before hiding
                val currentBl = try {
                    Settings.Secure.getString(context.contentResolver, "icon_blacklist")
                } catch (_: Throwable) {
                    null
                } ?: executeWithOutputSync("settings get secure icon_blacklist")?.takeIf { it != "null" }

                if (currentBl != fullBlacklistString && currentBl != ICON_BLACKLIST_FULL) {
                    val editor = prefs.edit()
                    if (!currentBl.isNullOrBlank()) {
                        editor.putBoolean(KEY_HAD_CUSTOM_BLACKLIST, true)
                        editor.putString(KEY_SAVED_ICON_BLACKLIST, currentBl)
                    } else {
                        editor.putBoolean(KEY_HAD_CUSTOM_BLACKLIST, false)
                        editor.remove(KEY_SAVED_ICON_BLACKLIST)
                    }
                    val currentPolicy = try {
                        Settings.Global.getString(context.contentResolver, "policy_control")
                    } catch (_: Throwable) {
                        null
                    }
                    if (!currentPolicy.isNullOrBlank() && currentPolicy != "immersive.status=*") {
                        editor.putString(KEY_SAVED_POLICY_CONTROL, currentPolicy)
                    } else {
                        editor.remove(KEY_SAVED_POLICY_CONTROL)
                    }
                    val currentBatteryPct = try {
                        Settings.System.getString(context.contentResolver, "status_bar_show_battery_percent")
                    } catch (_: Throwable) {
                        null
                    } ?: executeWithOutputSync("settings get system status_bar_show_battery_percent")?.takeIf { it != "null" }
                    if (!currentBatteryPct.isNullOrBlank()) {
                        editor.putString(KEY_SAVED_BATTERY_PERCENT, currentBatteryPct)
                    }
                    editor.apply()
                }
            }

            val hadCustomBlacklist = prefs.getBoolean(KEY_HAD_CUSTOM_BLACKLIST, false)
            val savedBlacklist = prefs.getString(KEY_SAVED_ICON_BLACKLIST, null)
            val savedPolicyControl = prefs.getString(KEY_SAVED_POLICY_CONTROL, null)
            val savedBatteryPercent = prefs.getString(KEY_SAVED_BATTERY_PERCENT, "1") ?: "1"

            // Try direct Settings modification if permission is granted
            if (hasWriteSecureSettings(context)) {
                try {
                    if (hidden) {
                        Settings.Secure.putString(context.contentResolver, "icon_blacklist", fullBlacklistString)
                        Settings.Global.putString(context.contentResolver, "policy_control", "immersive.status=*")
                        Settings.Secure.putString(context.contentResolver, "status_bar_show_notification_icon", "0")
                        Settings.Secure.putString(context.contentResolver, "show_notification_icon", "0")
                        runCatching {
                            Settings.System.putString(context.contentResolver, "status_bar_show_battery_percent", "0")
                        }
                    } else {
                        if (hadCustomBlacklist && !savedBlacklist.isNullOrBlank()) {
                            Settings.Secure.putString(context.contentResolver, "icon_blacklist", savedBlacklist)
                        } else {
                            // Passing null deletes the setting in Settings.Secure so SystemUI falls back to config_statusBarIconsToExclude
                            Settings.Secure.putString(context.contentResolver, "icon_blacklist", null)
                        }
                        Settings.Global.putString(context.contentResolver, "policy_control", savedPolicyControl)
                        Settings.Secure.putString(context.contentResolver, "status_bar_show_notification_icon", "1")
                        Settings.Secure.putString(context.contentResolver, "show_notification_icon", "1")
                        runCatching {
                            Settings.System.putString(context.contentResolver, "status_bar_show_battery_percent", savedBatteryPercent)
                        }
                    }
                    success = true
                } catch (e: Throwable) {
                    Log.w(TAG, "Direct Settings write failed, falling back to shell", e)
                }
            }

            // Run Shizuku shell commands including `cmd statusbar send-disable-flag` which hides
            // the entire status bar system-icons area (including the battery icon & percentage),
            // clock, and notification icons at the StatusBarManagerService level
            val commands = if (hidden) {
                listOf(
                    "cmd statusbar send-disable-flag system-icons clock notification-icons",
                    "settings put secure icon_blacklist \"$fullBlacklistString\"",
                    "settings put system status_bar_show_battery_percent 0",
                    "settings put global policy_control \"immersive.status=*\"",
                    "settings put secure status_bar_show_notification_icon 0",
                    "settings put secure show_notification_icon 0"
                )
            } else {
                val blCmd = if (hadCustomBlacklist && !savedBlacklist.isNullOrBlank()) {
                    "settings put secure icon_blacklist \"$savedBlacklist\""
                } else {
                    "settings delete secure icon_blacklist"
                }
                val policyCmd = if (!savedPolicyControl.isNullOrBlank()) {
                    "settings put global policy_control \"$savedPolicyControl\""
                } else {
                    "settings delete global policy_control"
                }
                listOf(
                    "cmd statusbar send-disable-flag none",
                    blCmd,
                    "settings put system status_bar_show_battery_percent $savedBatteryPercent",
                    policyCmd,
                    "settings put secure status_bar_show_notification_icon 1",
                    "settings put secure show_notification_icon 1"
                )
            }

            if (isShizukuAvailable() && hasShizukuPermission()) {
                for (cmd in commands) {
                    runShellCommand(cmd)
                }
                success = true
            }

            try {
                for (cmd in commands) {
                    val p = Runtime.getRuntime().exec(arrayOf("su", "-c", cmd))
                    p.waitFor()
                }
            } catch (_: Throwable) {}

            mainHandler.post { onResult?.invoke(success) }
        }
    }

    // Only notification icons; leaves the clock, network, battery and shade alone on AOSP.
    fun setNotificationIconsOnlyHidden(hidden: Boolean, onResult: (Boolean) -> Unit) {
        executor.execute {
            val success = isShizukuAvailable() && hasShizukuPermission() && runShellCommand(
                "cmd statusbar send-disable-flag " + (if (hidden) "notification-icons" else "none")
            )
            mainHandler.post { onResult(success) }
        }
    }

    private fun runShellCommand(command: String): Boolean {
        return try {
            val method = Shizuku::class.java.getDeclaredMethod(
                "newProcess",
                Array<String>::class.java,
                Array<String>::class.java,
                String::class.java
            )
            method.isAccessible = true
            val process = method.invoke(null, arrayOf("sh", "-c", command), null, null) as java.lang.Process
            val exitCode = process.waitFor()
            if (exitCode != 0) {
                val reader = BufferedReader(InputStreamReader(process.errorStream))
                val err = reader.readText()
                Log.e(TAG, "Command failed ($exitCode): $err")
            }
            exitCode == 0
        } catch (e: Throwable) {
            Log.e(TAG, "Error executing shell command via Shizuku: $command", e)
            false
        }
    }

    fun getShizukuLaunchIntent(context: Context): Intent? {
        val pm = context.packageManager
        return pm.getLaunchIntentForPackage("moe.shizuku.privileged.api")
    }

    fun openShizukuOrStore(context: Context) {
        val launchIntent = getShizukuLaunchIntent(context)
        if (launchIntent != null) {
            context.startActivity(launchIntent)
        } else {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://shizuku.rikka.app/download/")))
            } catch (_: Exception) {
            }
        }
    }
}
