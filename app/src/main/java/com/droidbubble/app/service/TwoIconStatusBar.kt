package com.droidbubble.app.service

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.SystemClock
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import com.droidbubble.app.island.IslandStateManager
import com.droidbubble.app.model.IslandSettings
import com.droidbubble.app.util.ShizukuHelper

/** Optional replacement for the left notification icon strip, not a SystemUI modification. */
internal class TwoIconStatusBar(private val context: Context) {
    companion object {
        private const val PREFS = "two_icon_status_bar"
        private const val ENABLED = "enabled"
        private const val RESTORE = "restore_pending"
        private fun prefs(context: Context) = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        fun enabled(context: Context) = prefs(context).getBoolean(ENABLED, false)
        fun setEnabled(context: Context, enabled: Boolean) {
            prefs(context).edit().putBoolean(ENABLED, enabled).apply()
        }
        private fun restorePending(context: Context) = prefs(context).getBoolean(RESTORE, false)
        private fun markRestore(context: Context, pending: Boolean) {
            prefs(context).edit().putBoolean(RESTORE, pending).commit()
        }
        // Called from main thread. Existing shell helper serializes hide/restore requests.
        fun restore(context: Context, result: (Boolean) -> Unit = {}) {
            if (ShizukuHelper.isSystemStatusBarIconsHidden(context)) {
                result(false)
                return
            }
            ShizukuHelper.setNotificationIconsOnlyHidden(false) { ok ->
                if (ok) markRestore(context, false)
                result(ok)
            }
        }
    }

    private var row: LinearLayout? = null
    private var manager: WindowManager? = null
    private var owner: IslandAccessibilityService? = null
    private var params: WindowManager.LayoutParams? = null
    private var iconKeys = emptyList<String>()
    private var hidden = false
    private var busy = false
    private var disposed = false
    private var retryAfter = 0L
    private fun dp(value: Float): Int = (value * context.resources.displayMetrics.density + 0.5f).toInt()

    fun refresh(settings: IslandSettings, running: Boolean) {
        if (disposed) return
        val accessibility = IslandAccessibilityService.instance
        val requested = running && settings.isEnabled && enabled(context)
        val listener = NotificationReceiverService.instance
        val notifications = if (requested) listener?.statusBarIconCandidates() else null
        val fullHide = ShizukuHelper.isSystemStatusBarIconsHidden(context)
        val usable = requested && accessibility != null &&
            notifications != null && ShizukuHelper.hasShizukuPermission() && !fullHide
        if (!usable) {
            removeRow()
            hidden = false
            if (!fullHide && !busy && restorePending(context) && SystemClock.elapsedRealtime() >= retryAfter && ShizukuHelper.hasShizukuPermission()) {
                busy = true
                restore(context) { ok ->
                    busy = false
                    hidden = false
                    retryAfter = SystemClock.elapsedRealtime() + if (ok) 0L else 5000L
                }
            }
            return
        }
        if (!restorePending(context)) hidden = false
        val orientationOk = context.resources.configuration.orientation != Configuration.ORIENTATION_LANDSCAPE
        val canShow = orientationOk && !settings.isLeftCutout && !settings.isRightCutout &&
            !IslandStateManager.isDeviceLocked.value && !IslandStateManager.isExpanded.value
        // Do not hide native icons unless our replacement window was successfully attached.
        if (!canShow) {
            row?.visibility = View.INVISIBLE
            return
        }
        val a = accessibility ?: return
        val candidates = notifications ?: return
        if (owner !== a || row?.isAttachedToWindow != true) {
            removeRow()
            if (!attach(a)) return
        }
        val view = row ?: return
        val lp = params ?: return
        // Clock and island geometry are estimated; keep a gap before the configured pill.
        val leftDp = 76f
        val screenDp = context.resources.configuration.screenWidthDp.toFloat()
        val islandLeft = screenDp / 2f + settings.horizontalOffsetDp - settings.widthDp / 2f
        val available = islandLeft - leftDp - 3f
        val count = when {
            available >= 34f -> 2
            available >= 16f -> 1
            else -> 0
        }
        if (count == 0) {
            view.visibility = View.INVISIBLE
            // If the custom geometry does not fit, keep the native strip instead.
            if (!busy && restorePending(context)) {
                busy = true
                restore(context) { busy = false; hidden = false }
            }
            return
        }
        val newY = dp(settings.topOffsetDp + (settings.heightDp - 18f) / 2f)
        val newWidth = dp(if (count == 2) 34f else 16f)
        if (lp.y != newY || lp.width != newWidth) {
            lp.y = newY
            lp.width = newWidth
            runCatching { manager?.updateViewLayout(view, lp) }
        }
        val selected = candidates.take(count)
        val keys = selected.map { "${it.key}:${it.postTime}:${it.notification.smallIcon}" }
        if (keys != iconKeys) {
            view.removeAllViews()
            selected.forEachIndexed { index, sbn ->
                val drawable = runCatching { sbn.notification.smallIcon?.loadDrawable(a)?.mutate() }.getOrNull()
                if (drawable != null) {
                    drawable.setTint(Color.WHITE)
                    val image = ImageView(a).apply {
                        scaleType = ImageView.ScaleType.FIT_CENTER
                        setImageDrawable(drawable)
                        importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
                    }
                    val child = LinearLayout.LayoutParams(dp(14f), dp(14f)).apply {
                        if (index > 0) marginStart = dp(4f)
                    }
                    view.addView(image, child)
                }
            }
            iconKeys = keys
        }
        view.visibility = if (hidden) View.VISIBLE else View.INVISIBLE
        if (!hidden && !busy && SystemClock.elapsedRealtime() >= retryAfter) {
            busy = true
            markRestore(context, true)
            ShizukuHelper.setNotificationIconsOnlyHidden(true) { ok ->
                busy = false
                if (!disposed) {
                    hidden = ok
                    retryAfter = SystemClock.elapsedRealtime() + if (ok) 0L else 5000L
                }
            }
        }
    }

    private fun attach(service: IslandAccessibilityService): Boolean {
        val wm = service.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val view = LinearLayout(service).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            visibility = View.INVISIBLE
            importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
        }
        val lp = WindowManager.LayoutParams(
            dp(34f), dp(18f), WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.LEFT
            x = dp(76f)
            y = dp(12f)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            } else {
                layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
            windowAnimations = 0
        }
        return try {
            wm.addView(view, lp)
            row = view
            manager = wm
            owner = service
            params = lp
            true
        } catch (_: Exception) {
            false
        }
    }

    private fun removeRow() {
        row?.let { v -> runCatching { manager?.removeViewImmediate(v) } }
        row = null
        manager = null
        owner = null
        params = null
        iconKeys = emptyList()
    }

    fun close() {
        disposed = true
        removeRow()
        if (restorePending(context)) restore(context)
    }
}
