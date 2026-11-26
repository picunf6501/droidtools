package com.droidbubble.app.ui.main

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Velocity
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.TextButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.AlertDialog
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.foundation.lazy.rememberLazyListState

import com.droidbubble.app.island.M3CookieShape
import com.droidbubble.app.island.M3Cookie6Shape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material.icons.filled.WifiTethering
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.Screenshot
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.droidbubble.app.R


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.zIndex
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Menu
import android.view.HapticFeedbackConstants
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.draw.alpha
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import java.util.Locale
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.FitScreen
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Subtitles
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.LinearScale
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.RoundedCorner
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material.icons.filled.ScreenSearchDesktop
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.filled.VerticalAlignTop
import androidx.compose.material.icons.filled.Vibration
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.FontDownload
import androidx.compose.material3.RadioButton
import com.droidbubble.app.theme.ISLAND_FONTS
import com.droidbubble.app.model.DEFAULT_FEATURE_PRIORITIES
import com.droidbubble.app.model.DesignLanguage
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayCircleOutline
import com.droidbubble.app.model.DEFAULT_MEDIA_LAYERS
import com.droidbubble.app.island.ExpandedMediaView
import com.droidbubble.app.island.IslandStateManager
import com.droidbubble.app.island.MediaBottomWaveOverlay
import com.droidbubble.app.island.extractMediaAccentColor
import com.droidbubble.app.island.createBlurredBitmap
import com.droidbubble.app.droidbar.MostUsedAppsHelper
import com.droidbubble.app.droidbar.BarAppInfo
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.filled.VerticalSplit
import com.droidbubble.app.model.MediaInfo
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.droidbubble.app.model.IslandSettings
import com.droidbubble.app.service.IslandOverlayService
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

enum class MainScreenPage {
    TOOLS_ROOT,
    DROIDBUBBLE,
    DIMENSIONS,
    APPEARANCE,
    GENERAL,
    MEDIA_PLAYER,
    DROIDBAR,
    DROIDBAR_APPS,
    TASKS,
    DISPLAY_SETTINGS,
    SOUND_AND_VIBRATIONS,
    BATTERY_AND_POWER_SAVING,
    NOTIFICATIONS
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun UpdateCardItem(
    updateState: MainScreenViewModel.UpdateState,
    onCheck: () -> Unit,
    onOpenSheet: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDownloading = updateState is MainScreenViewModel.UpdateState.Downloading
    val isChecking = updateState is MainScreenViewModel.UpdateState.Checking
    val isAvailable = updateState is MainScreenViewModel.UpdateState.Available

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .clickable(enabled = !isDownloading && !isChecking) {
                if (isAvailable) {
                    onOpenSheet()
                } else {
                    onCheck()
                }
            },
        shape = RoundedCornerShape(28.dp),
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shadowElevation = 2.dp
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent,
                headlineColor = MaterialTheme.colorScheme.onPrimary,
                leadingIconColor = MaterialTheme.colorScheme.onPrimary,
                trailingIconColor = MaterialTheme.colorScheme.onPrimary
            ),
            headlineContent = {
                Text(
                    text = when (updateState) {
                        is MainScreenViewModel.UpdateState.Checking -> "Checking for updates..."
                        is MainScreenViewModel.UpdateState.UpToDate -> "Up to date"
                        is MainScreenViewModel.UpdateState.Available -> {
                            val rel = updateState.release
                            "Update available (${rel.version})"
                        }
                        is MainScreenViewModel.UpdateState.Downloading -> {
                            val p = (updateState.progress * 100).roundToInt()
                            "Downloading update ($p%)..."
                        }
                        is MainScreenViewModel.UpdateState.Error -> "Check for Updates"
                        else -> "Check for Updates"
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            leadingContent = {
                when (updateState) {
                    is MainScreenViewModel.UpdateState.Checking -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.5.dp
                        )
                    }
                    is MainScreenViewModel.UpdateState.UpToDate -> {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Up to date",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    is MainScreenViewModel.UpdateState.Available -> {
                        Icon(
                            imageVector = Icons.Default.SystemUpdate,
                            contentDescription = "Update Available",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Check for Updates",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            trailingContent = {
                if (updateState is MainScreenViewModel.UpdateState.Available) {
                    val rel = updateState.release
                    if (rel.isBeta) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.22f)
                        ) {
                            Text(
                                text = "BETA",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val view = LocalView.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val settings by viewModel.settings.collectAsState()
    val pendingPermissions by viewModel.pendingPermissions.collectAsState()
    val installedApps by viewModel.installedApps.collectAsState()
    val isLoadingApps by viewModel.isLoadingApps.collectAsState()
    val updateState by viewModel.updateState.collectAsState()
    val showUpdateSheet by viewModel.showUpdateSheet.collectAsState()

    var showAppFilterSheet by remember { mutableStateOf(false) }
    var appFilterQuery by remember { mutableStateOf("") }

    var showDroidbubblePage by remember { mutableStateOf(false) }
    var showDimensionsPage by remember { mutableStateOf(false) }
    var showAppearancePage by remember { mutableStateOf(false) }
    var showGeneralPage by remember { mutableStateOf(false) }
    var showMediaPlayerPage by remember { mutableStateOf(false) }
    var showDroidbarPage by remember { mutableStateOf(false) }
    var showDroidbarAppsPage by remember { mutableStateOf(false) }
    var showTasksPage by remember { mutableStateOf(false) }
    var showDisplaySettingsPage by remember { mutableStateOf(false) }
    var showSoundAndVibrationsPage by remember { mutableStateOf(false) }
    var showBatteryAndPowerSavingPage by remember { mutableStateOf(false) }
    var showNotificationsPage by remember { mutableStateOf(false) }
    var selectedRootTab by remember { mutableIntStateOf(0) }
    var showFeedbackSheet by remember { mutableStateOf(false) }
    var feedbackType by remember { mutableStateOf(FeedbackType.BUG) }
    var feedbackTitle by remember { mutableStateOf("") }
    var feedbackDescription by remember { mutableStateOf("") }
    var attachmentUri by remember { mutableStateOf<Uri?>(null) }
    var attachmentName by remember { mutableStateOf<String?>(null) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            attachmentUri = uri
            attachmentName = getAttachmentFileName(context, uri)
        }
    }

    // Re-check permissions on every onResume
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.checkPermissions(context)
                viewModel.refreshUpdateStateOnResume(context)
                if ((settings.isEnabled || settings.droidbarEnabled) && PermissionHelper.isOverlayGranted(context)) {
                    IslandOverlayService.start(context)
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val currentNavPage = when {
        showNotificationsPage -> MainScreenPage.NOTIFICATIONS
        showBatteryAndPowerSavingPage -> MainScreenPage.BATTERY_AND_POWER_SAVING
        showDisplaySettingsPage -> MainScreenPage.DISPLAY_SETTINGS
        showSoundAndVibrationsPage -> MainScreenPage.SOUND_AND_VIBRATIONS
        showTasksPage -> MainScreenPage.TASKS
        showDroidbarAppsPage -> MainScreenPage.DROIDBAR_APPS
        showDroidbarPage -> MainScreenPage.DROIDBAR
        showMediaPlayerPage -> MainScreenPage.MEDIA_PLAYER
        showDimensionsPage -> MainScreenPage.DIMENSIONS
        showAppearancePage -> MainScreenPage.APPEARANCE
        showGeneralPage -> MainScreenPage.GENERAL
        showDroidbubblePage -> MainScreenPage.DROIDBUBBLE
        else -> MainScreenPage.TOOLS_ROOT
    }

    AnimatedContent(
        targetState = currentNavPage,
        transitionSpec = {
            val isNavigatingForward = when {
                initialState == MainScreenPage.TOOLS_ROOT && targetState != MainScreenPage.TOOLS_ROOT -> true
                initialState == MainScreenPage.DROIDBUBBLE && (targetState == MainScreenPage.DIMENSIONS || targetState == MainScreenPage.APPEARANCE || targetState == MainScreenPage.GENERAL || targetState == MainScreenPage.MEDIA_PLAYER) -> true
                initialState == MainScreenPage.APPEARANCE && targetState == MainScreenPage.MEDIA_PLAYER -> true
                initialState == MainScreenPage.DROIDBAR && targetState == MainScreenPage.DROIDBAR_APPS -> true
                else -> false
            }
            if (isNavigatingForward) {
                (slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                ) + fadeIn(animationSpec = tween(220)))
                    .togetherWith(
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth / 3 },
                            animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                        ) + fadeOut(animationSpec = tween(180))
                    )
            } else {
                (slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth / 3 },
                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                ) + fadeIn(animationSpec = tween(220)))
                    .togetherWith(
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                        ) + fadeOut(animationSpec = tween(180))
                    )
            }
        },
        label = "subPageTransition"
    ) { page ->
        when (page) {
            MainScreenPage.DIMENSIONS -> {
                DimensionsPage(
                    settings = settings,
                    onUpdateSettings = { viewModel.updateSettings(it) },
                    onBack = { showDimensionsPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.APPEARANCE -> {
                AppearancePage(
                    settings = settings,
                    onUpdateSettings = { viewModel.updateSettings(it) },
                    onNavigateToMediaPlayer = { showMediaPlayerPage = true },
                    onBack = { showAppearancePage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.MEDIA_PLAYER -> {
                MediaPlayerCustomizationPage(
                    settings = settings,
                    onUpdateSettings = { viewModel.updateSettings(it) },
                    onBack = { showMediaPlayerPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.DROIDBAR -> {
                DroidbarPage(
                    settings = settings,
                    onUpdateSettings = { viewModel.updateSettings(it) },
                    onOpenAppsConfig = { showDroidbarAppsPage = true },
                    onBack = { showDroidbarPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.DROIDBAR_APPS -> {
                DroidbarAppsConfigPage(
                    settings = settings,
                    onUpdateSettings = { viewModel.updateSettings(it) },
                    onBack = { showDroidbarAppsPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.TASKS -> {
                com.droidbubble.app.tasks.TasksPage(
                    onBack = { showTasksPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.DISPLAY_SETTINGS -> {
                com.droidbubble.app.tools.DisplaySettingsPage(
                    onBack = { showDisplaySettingsPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.SOUND_AND_VIBRATIONS -> {
                com.droidbubble.app.tools.SoundAndVibrationsPage(
                    onBack = { showSoundAndVibrationsPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.BATTERY_AND_POWER_SAVING -> {
                com.droidbubble.app.tools.BatteryAndPowerSavingPage(
                    onBack = { showBatteryAndPowerSavingPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.NOTIFICATIONS -> {
                com.droidbubble.app.tools.NotificationsSettingsPage(
                    onBack = { showNotificationsPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.GENERAL -> {
                GeneralPage(
                    settings = settings,
                    onUpdateSettings = { viewModel.updateSettings(it) },
                    onBack = { showGeneralPage = false },
                    modifier = modifier
                )
            }
            MainScreenPage.TOOLS_ROOT -> {
                BackHandler(enabled = selectedRootTab != 0) {
                    selectedRootTab = 0
                }
                val toolsListState = rememberLazyListState()
                val deviceInfoListState = rememberLazyListState()
                val githubUpdatesListState = rememberLazyListState()
                val activeListState = when (selectedRootTab) {
                    0 -> toolsListState
                    1 -> deviceInfoListState
                    else -> githubUpdatesListState
                }
                var overscrollOffset by remember { mutableFloatStateOf(0f) }
                var lastHapticStep by remember { mutableIntStateOf(0) }

                LaunchedEffect(selectedRootTab) {
                    if (selectedRootTab != 0) {
                        overscrollOffset = 0f
                        lastHapticStep = 0
                    }
                }

                val animatedOverscroll by animateFloatAsState(
                    targetValue = if (selectedRootTab == 0) overscrollOffset else 0f,
                    animationSpec = spring(dampingRatio = 0.72f, stiffness = Spring.StiffnessMediumLow),
                    label = "overscrollOffset"
                )

                val nestedScrollConnection = remember(activeListState, selectedRootTab) {
                    object : NestedScrollConnection {
                        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                            if (selectedRootTab != 0) return Offset.Zero
                            if (available.y < 0 && overscrollOffset > 0f) {
                                val consumed = available.y.coerceAtLeast(-overscrollOffset)
                                overscrollOffset += consumed
                                val step = (overscrollOffset / 24f).toInt()
                                if (step < lastHapticStep) {
                                    lastHapticStep = step
                                }
                                return Offset(0f, consumed)
                            }
                            return Offset.Zero
                        }

                        override fun onPostScroll(
                            consumed: Offset,
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            if (selectedRootTab != 0) return Offset.Zero
                            if (available.y > 0 && activeListState.firstVisibleItemIndex == 0 && activeListState.firstVisibleItemScrollOffset == 0) {
                                val delta = available.y * 0.45f
                                val nextOffset = (overscrollOffset + delta).coerceAtMost(160f)
                                val nextStep = (nextOffset / 24f).toInt()
                                if (nextStep > lastHapticStep && nextStep <= 3) {
                                    lastHapticStep = nextStep
                                    view.performHapticFeedback(
                                        if (nextStep == 3) HapticFeedbackConstants.CONFIRM else HapticFeedbackConstants.CLOCK_TICK
                                    )
                                }
                                overscrollOffset = nextOffset
                                return Offset(0f, available.y)
                            }
                            return Offset.Zero
                        }

                        override suspend fun onPreFling(available: Velocity): Velocity {
                            overscrollOffset = 0f
                            lastHapticStep = 0
                            return Velocity.Zero
                        }

                        override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                            overscrollOffset = 0f
                            lastHapticStep = 0
                            return Velocity.Zero
                        }
                    }
                }

                val expandProgress = (animatedOverscroll / 70f).coerceIn(0f, 1f)
                val appVersion = remember { com.droidbubble.app.util.UpdateManager.getCurrentVersion(context) }
                val titleSize = (20f + (expandProgress * 8f)).sp
                val topBarHeight = (64.dp + (expandProgress * 36).dp)
                val density = LocalDensity.current

                Scaffold(
                    modifier = modifier.nestedScroll(nestedScrollConnection),
                    topBar = {
                        if (selectedRootTab == 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(topBarHeight)
                                    .background(MaterialTheme.colorScheme.background)
                                    .statusBarsPadding()
                                    .padding(horizontal = 20.dp),
                                contentAlignment = BiasAlignment(
                                    horizontalBias = -1f + expandProgress,
                                    verticalBias = 0f
                                )
                            ) {
                                Column(
                                    horizontalAlignment = if (expandProgress > 0.35f) Alignment.CenterHorizontally else Alignment.Start,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Droidtools",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = titleSize,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        letterSpacing = if (expandProgress > 0.05f) (0.5.sp * expandProgress) else 0.sp
                                    )
                                    if (expandProgress > 0.02f) {
                                        Text(
                                            text = "v$appVersion",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = expandProgress),
                                            modifier = Modifier.graphicsLayer { alpha = expandProgress }
                                        )
                                    }
                                }
                            }
                        } else {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                                    .statusBarsPadding()
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        var contentHeightPx by remember { mutableFloatStateOf(0f) }
                        val blurRegionPx = with(density) { 125.dp.toPx() }
                        val maxSigmaPx = with(density) { 16.dp.toPx() }

                        val progressiveBlurModifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && contentHeightPx > 0f) {
                            val startY = (contentHeightPx - blurRegionPx).coerceAtLeast(0f)
                            val endY = contentHeightPx
                            Modifier.graphicsLayer {
                                clip = true
                                val shader = android.graphics.RuntimeShader(PROGRESSIVE_BOTTOM_BLUR_AGSL).apply {
                                    setFloatUniform("uStartY", startY)
                                    setFloatUniform("uEndY", endY)
                                    setFloatUniform("uMaxRadius", maxSigmaPx)
                                }
                                renderEffect = android.graphics.RenderEffect
                                    .createRuntimeShaderEffect(shader, "content")
                                    .asComposeRenderEffect()
                            }
                        } else {
                            Modifier
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .onGloballyPositioned { coords ->
                                    contentHeightPx = coords.size.height.toFloat()
                                }
                                .then(progressiveBlurModifier)
                        ) {
                            AnimatedContent(
                                targetState = selectedRootTab,
                                transitionSpec = {
                                    if (targetState > initialState) {
                                        (slideInHorizontally(
                                            initialOffsetX = { fullWidth -> fullWidth / 3 },
                                            animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                                        ) + fadeIn(animationSpec = tween(200)))
                                            .togetherWith(
                                                slideOutHorizontally(
                                                    targetOffsetX = { fullWidth -> -fullWidth / 3 },
                                                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                                                ) + fadeOut(animationSpec = tween(160))
                                            )
                                    } else {
                                        (slideInHorizontally(
                                            initialOffsetX = { fullWidth -> -fullWidth / 3 },
                                            animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                                        ) + fadeIn(animationSpec = tween(200)))
                                            .togetherWith(
                                                slideOutHorizontally(
                                                    targetOffsetX = { fullWidth -> fullWidth / 3 },
                                                    animationSpec = spring(stiffness = Spring.StiffnessMediumLow, dampingRatio = 0.85f)
                                                ) + fadeOut(animationSpec = tween(160))
                                            )
                                    }
                                },
                                label = "rootBottomNavContent"
                            ) { tabIndex ->
                                if (tabIndex == 0) {
                                    val toolsIsScrolled by remember {
                                        derivedStateOf {
                                            toolsListState.firstVisibleItemIndex > 0 || toolsListState.firstVisibleItemScrollOffset > 6
                                        }
                                    }
                                    ProgressiveTopBlurBox(
                                        isScrolled = toolsIsScrolled,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                    LazyColumn(
                                        state = toolsListState,
                                        modifier = Modifier.fillMaxSize(),
                                        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 108.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        item {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                UpdateCardItem(
                                                    updateState = updateState,
                                                    onCheck = { viewModel.checkForUpdates(context) },
                                                    onOpenSheet = { viewModel.openUpdateSheet() },
                                                    modifier = Modifier.weight(1f)
                                                )

                                                Surface(
                                                    modifier = Modifier
                                                        .size(56.dp)
                                                        .clip(CircleShape)
                                                        .clickable { showFeedbackSheet = true },
                                                    shape = CircleShape,
                                                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                                                    contentColor = MaterialTheme.colorScheme.primary,
                                                    shadowElevation = 2.dp
                                                ) {
                                                    Box(
                                                        modifier = Modifier.fillMaxSize(),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Icon(
                                                            imageVector = Icons.Default.Feedback,
                                                            contentDescription = "Send Feedback",
                                                            tint = MaterialTheme.colorScheme.primary,
                                                            modifier = Modifier.size(22.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        item {
                                            SectionHeader(
                                                title = "Utilities",
                                                icon = Icons.Default.Widgets
                                            )
                                        }

                                        item {
                                            Column(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalArrangement = Arrangement.spacedBy(2.dp)
                                            ) {
                                                SettingsCardItem(position = GroupPosition.TOP) {
                                                    ListItem(
                                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                        headlineContent = { Text("Droidbubble", fontWeight = FontWeight.SemiBold) },
                                                        leadingContent = {
                                                            CookieIconBox(icon = Icons.Default.Widgets)
                                                        },
                                                        trailingContent = {
                                                            Icon(
                                                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                                                contentDescription = "Open Droidbubble",
                                                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                                                modifier = Modifier.size(16.dp)
                                                            )
                                                        },
                                                        modifier = Modifier.clickable { showDroidbubblePage = true }
                                                    )
                                                }

                                                SettingsCardItem(position = GroupPosition.MIDDLE) {
                                                    ListItem(
                                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                        headlineContent = { Text("Droidbar", fontWeight = FontWeight.SemiBold) },
                                                        leadingContent = {
                                                            CookieIconBox(icon = Icons.Default.VerticalSplit)
                                                        },
                                                        trailingContent = {
                                                            Icon(
                                                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                                                contentDescription = "Open Droidbar",
                                                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                                                modifier = Modifier.size(16.dp)
                                                            )
                                                        },
                                                        modifier = Modifier.clickable { showDroidbarPage = true }
                                                    )
                                                }

                                                SettingsCardItem(position = GroupPosition.MIDDLE) {
                                                    ListItem(
                                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                        headlineContent = { Text("Tasks", fontWeight = FontWeight.SemiBold) },
                                                        leadingContent = {
                                                            CookieIconBox(icon = Icons.Default.Bolt)
                                                        },
                                                        trailingContent = {
                                                            Icon(
                                                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                                                contentDescription = "Open Tasks",
                                                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                                                modifier = Modifier.size(16.dp)
                                                            )
                                                        },
                                                        modifier = Modifier.clickable { showTasksPage = true }
                                                    )
                                                }

                                                SettingsCardItem(position = GroupPosition.MIDDLE) {
                                                    ListItem(
                                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                        headlineContent = { Text("Display settings", fontWeight = FontWeight.SemiBold) },
                                                        leadingContent = {
                                                            CookieIconBox(icon = Icons.Default.WbSunny)
                                                        },
                                                        trailingContent = {
                                                            Icon(
                                                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                                                contentDescription = "Open Display settings",
                                                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                                                modifier = Modifier.size(16.dp)
                                                            )
                                                        },
                                                        modifier = Modifier.clickable { showDisplaySettingsPage = true }
                                                    )
                                                }

                                                SettingsCardItem(position = GroupPosition.MIDDLE) {
                                                    ListItem(
                                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                        headlineContent = { Text("Sound and vibrations", fontWeight = FontWeight.SemiBold) },
                                                        leadingContent = {
                                                            CookieIconBox(icon = Icons.Default.VolumeUp)
                                                        },
                                                        trailingContent = {
                                                            Icon(
                                                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                                                contentDescription = "Open Sound and vibrations",
                                                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                                                modifier = Modifier.size(16.dp)
                                                            )
                                                        },
                                                        modifier = Modifier.clickable { showSoundAndVibrationsPage = true }
                                                    )
                                                }

                                                SettingsCardItem(position = GroupPosition.MIDDLE) {
                                                    ListItem(
                                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                        headlineContent = { Text("Battery and power saving", fontWeight = FontWeight.SemiBold) },
                                                        leadingContent = {
                                                            CookieIconBox(icon = Icons.Default.BatteryChargingFull)
                                                        },
                                                        trailingContent = {
                                                            Icon(
                                                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                                                contentDescription = "Open Battery and power saving",
                                                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                                                modifier = Modifier.size(16.dp)
                                                            )
                                                        },
                                                        modifier = Modifier.clickable { showBatteryAndPowerSavingPage = true }
                                                    )
                                                }

                                                SettingsCardItem(position = GroupPosition.BOTTOM) {
                                                    ListItem(
                                                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                                        headlineContent = { Text("Notifications", fontWeight = FontWeight.SemiBold) },
                                                        leadingContent = {
                                                            CookieIconBox(icon = Icons.Default.Notifications)
                                                        },
                                                        trailingContent = {
                                                            Icon(
                                                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                                                contentDescription = "Open Notifications",
                                                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                                                modifier = Modifier.size(16.dp)
                                                            )
                                                        },
                                                        modifier = Modifier.clickable { showNotificationsPage = true }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    }
                                } else if (tabIndex == 1) {
                                    com.droidbubble.app.tools.DeviceInfoTabContent(
                                        listState = deviceInfoListState
                                    )
                                } else {
                                    com.droidbubble.app.updates.GithubUpdatesTabContent(
                                        listState = githubUpdatesListState
                                    )
                                }
                            }
                        }

                        // Progressive bottom gradient scrim below the 2-tab bar
                        val bgColor = MaterialTheme.colorScheme.background
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .height(115.dp)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            bgColor.copy(alpha = 0.35f),
                                            bgColor.copy(alpha = 0.78f),
                                            bgColor.copy(alpha = 0.96f)
                                        )
                                    )
                                )
                        )

                        ShortFloatingBottomNav(
                            selectedTab = selectedRootTab,
                            onSelectTab = { newTab ->
                                if (selectedRootTab != newTab) {
                                    view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
                                    selectedRootTab = newTab
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .navigationBarsPadding()
                                .padding(bottom = 16.dp)
                        )
                    }
                }
            }

            MainScreenPage.DROIDBUBBLE -> {
                BackHandler(onBack = { showDroidbubblePage = false })
                var isDraggingIslandActivity by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = modifier,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Droidbubble",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { showDroidbubblePage = false }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back to Droidtools"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { showFeedbackSheet = true }) {
                                    Icon(
                                        imageVector = Icons.Default.Feedback,
                                        contentDescription = "Send Feedback",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background
                            )
                        )
                    }
                ) { innerPadding ->
        val droidbubbleListState = rememberLazyListState()
        val droidbubbleIsScrolled by remember {
            derivedStateOf {
                droidbubbleListState.firstVisibleItemIndex > 0 || droidbubbleListState.firstVisibleItemScrollOffset > 6
            }
        }
        ProgressiveTopBlurBox(
            isScrolled = droidbubbleIsScrolled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
        LazyColumn(
            state = droidbubbleListState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = !isDraggingIslandActivity,
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // SECTION 1: PERMISSIONS (Hides individual item once granted, hides whole section when all granted!)
            if (pendingPermissions.isNotEmpty()) {
                item {
                    SectionHeader(
                        title = "Required Setup",
                        icon = Icons.Default.Security,
                        badge = "${pendingPermissions.size} Pending",
                        badgeColor = MaterialTheme.colorScheme.error
                    )
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        pendingPermissions.forEachIndexed { index, perm ->
                            val position = when {
                                pendingPermissions.size == 1 -> GroupPosition.SINGLE
                                index == 0 -> GroupPosition.TOP
                                index == pendingPermissions.size - 1 -> GroupPosition.BOTTOM
                                else -> GroupPosition.MIDDLE
                            }
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = getGroupedShape(position),
                                color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.25f)
                            ) {
                                ListItem(
                                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                    headlineContent = {
                                        Text(perm.title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                    },
                                    leadingContent = {
                                        Box(
                                            modifier = Modifier
                                                .size(40.dp)
                                                .clip(CircleShape)
                                                .background(MaterialTheme.colorScheme.errorContainer),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Warning,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.error,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    },
                                    trailingContent = {
                                        Button(
                                            onClick = {
                                                runCatching { context.startActivity(perm.createIntent(context)) }
                                            },
                                            shape = RoundedCornerShape(12.dp)
                                        ) {
                                            Text("Grant", fontWeight = FontWeight.SemiBold)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 2: MASTER ENABLE SWITCH
            item {
                SettingsCardItem(position = GroupPosition.SINGLE, isActive = settings.isEnabled) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text("Enable Island", fontWeight = FontWeight.SemiBold) },
                        leadingContent = {
                            Icon(Icons.Default.PowerSettingsNew, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        },
                        trailingContent = {
                            Switch(
                                checked = settings.isEnabled,
                                onCheckedChange = { enabled ->
                                    viewModel.toggleEnabled(context, enabled)
                                }
                            )
                        }
                    )
                }
            }



            // TABS: ISLAND DIMENSIONS & APPEARANCE
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier = Modifier.clickable { showDimensionsPage = true },
                            headlineContent = { Text("Island Dimensions", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(icon = Icons.Default.Straighten)
                            },
                            trailingContent = {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Open Island Dimensions",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier = Modifier.clickable { showAppearancePage = true },
                            headlineContent = { Text("Appearance & Feedback", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(icon = Icons.Default.Palette)
                            },
                            trailingContent = {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Open Appearance & Feedback",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier = Modifier.clickable { showMediaPlayerPage = true },
                            headlineContent = { Text("Media Player Customisation", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(icon = Icons.Default.MusicNote)
                            },
                            trailingContent = {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Open Media Player Customisation",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier = Modifier.clickable { showGeneralPage = true },
                            headlineContent = { Text("General", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(icon = Icons.Default.Tune)
                            },
                            trailingContent = {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Open General",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )
                    }
                }
            }

            // SECTION 5A: ISLAND ACTIVITIES (DRAG TO REORDER PRIORITY)
            item {
                SectionHeader(
                    title = "Island Activities",
                    icon = Icons.Default.Apps
                )
            }

            item {
                val reorderableKeys = remember(settings.featurePriorities) {
                    val valid = DEFAULT_FEATURE_PRIORITIES
                    val list = settings.featurePriorities.filter { it in valid }.toMutableList()
                    for (v in valid) {
                        if (v !in list) list.add(v)
                    }
                    list.toMutableStateList()
                }
                var draggingKey by remember { mutableStateOf<String?>(null) }
                var dragOffset by remember { mutableStateOf(0f) }
                var itemHeightPx by remember { mutableStateOf(0f) }

                LaunchedEffect(settings.featurePriorities) {
                    if (draggingKey == null) {
                        val valid = DEFAULT_FEATURE_PRIORITIES
                        val list = settings.featurePriorities.filter { it in valid }.toMutableList()
                        for (v in valid) {
                            if (v !in list) list.add(v)
                        }
                        if (reorderableKeys.toList() != list) {
                            reorderableKeys.clear()
                            reorderableKeys.addAll(list)
                        }
                    }
                }

                val density = LocalDensity.current
                val cardGapPx = with(density) { 4.dp.toPx() }
                val effectiveCardHeight = if (itemHeightPx > 0f) itemHeightPx else with(density) { 64.dp.toPx() }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    reorderableKeys.forEachIndexed { index, featureKey ->
                        key(featureKey) {
                            val currentKey = featureKey
                            val isBeingDragged = draggingKey == currentKey
                            val zIdx = if (isBeingDragged) 10f else 1f

                            val itemPosition = when {
                                index == 0 -> GroupPosition.TOP
                                index == reorderableKeys.size - 1 -> GroupPosition.BOTTOM
                                else -> GroupPosition.MIDDLE
                            }

                            val isFeatureChecked = when (currentKey) {
                                "CALL" -> settings.showCalls
                                "MEDIA" -> settings.showMedia
                                "NOTIFICATION" -> settings.showNotifications
                                "PROGRESS" -> settings.showLiveProgress
                                "RECORDING" -> settings.showScreenRecording
                                "FLASHLIGHT" -> settings.showFlashlight
                                "PERSISTENT_CHARGING" -> settings.persistentCharging
                                "ALARM" -> settings.showAlarm
                                "FPS" -> settings.showFps
                                "WEATHER" -> settings.showWeather
                                "NETWORK_SPEED" -> settings.showNetworkSpeed
                                else -> false
                            }

                            val featureTitle = when (currentKey) {
                                "CALL" -> "Phone Calls"
                                "MEDIA" -> "Media Player"
                                "NOTIFICATION" -> "Incoming Notifications"
                                "PROGRESS" -> "Live Progress & Activities"
                                "RECORDING" -> "Screen Recording Indicator"
                                "FLASHLIGHT" -> "Flashlight Indicator"
                                "PERSISTENT_CHARGING" -> "Persistent Charging"
                                "ALARM" -> "Upcoming Alarms"
                                "FPS" -> "FPS Counter"
                                "WEATHER" -> "Weather & Temperature"
                                "NETWORK_SPEED" -> "Wi-Fi & Network Speed"
                                else -> currentKey
                            }

                            val featureIcon = when (currentKey) {
                                "CALL" -> Icons.Default.Call
                                "MEDIA" -> Icons.Default.MusicNote
                                "NOTIFICATION" -> Icons.Default.Notifications
                                "PROGRESS" -> Icons.Default.Animation
                                "RECORDING" -> Icons.Default.Videocam
                                "FLASHLIGHT" -> Icons.Default.FlashlightOn
                                "PERSISTENT_CHARGING" -> Icons.Default.Bolt
                                "ALARM" -> Icons.Default.Alarm
                                "FPS" -> Icons.Default.Speed
                                "WEATHER" -> Icons.Default.WbSunny
                                "NETWORK_SPEED" -> Icons.Default.Wifi
                                else -> Icons.Default.Apps
                            }

                            val cardModifier = Modifier
                                .fillMaxWidth()
                                .zIndex(zIdx)
                                .onGloballyPositioned { coords ->
                                    if (!isBeingDragged && coords.size.height > 0) {
                                        itemHeightPx = coords.size.height.toFloat() + cardGapPx
                                    }
                                }
                                .graphicsLayer {
                                    if (isBeingDragged) {
                                        translationY = dragOffset
                                        scaleX = 1.02f
                                        scaleY = 1.02f
                                        shadowElevation = 14.dp.toPx()
                                    }
                                }

                            SettingsCardItem(
                                position = if (isBeingDragged) GroupPosition.SINGLE else itemPosition,
                                modifier = cardModifier,
                                isActive = isFeatureChecked
                            ) {
                                ListItem(
                                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                    headlineContent = { Text(featureTitle, fontWeight = FontWeight.SemiBold) },
                                    leadingContent = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(36.dp)
                                                    .pointerInput(currentKey) {
                                                        detectVerticalDragGestures(
                                                            onDragStart = {
                                                                isDraggingIslandActivity = true
                                                                draggingKey = currentKey
                                                                dragOffset = 0f
                                                                view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                                                            },
                                                            onDragEnd = {
                                                                if (draggingKey == currentKey) {
                                                                    val updatedList = reorderableKeys.toList()
                                                                    viewModel.updateSettings { it.copy(featurePriorities = updatedList) }
                                                                    view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                                                                }
                                                                draggingKey = null
                                                                isDraggingIslandActivity = false
                                                                dragOffset = 0f
                                                            },
                                                            onDragCancel = {
                                                                draggingKey = null
                                                                isDraggingIslandActivity = false
                                                                dragOffset = 0f
                                                            },
                                                            onVerticalDrag = { change, dragAmount ->
                                                                change.consume()
                                                                if (draggingKey != currentKey) return@detectVerticalDragGestures
                                                                dragOffset += dragAmount
                                                                val currentIndex = reorderableKeys.indexOf(currentKey)
                                                                if (currentIndex == -1) return@detectVerticalDragGestures
                                                                val step = if (itemHeightPx > 0f) itemHeightPx else effectiveCardHeight
                                                                val threshold = step * 0.5f
                                                                if (dragOffset > threshold && currentIndex < reorderableKeys.lastIndex) {
                                                                    val item = reorderableKeys.removeAt(currentIndex)
                                                                    reorderableKeys.add(currentIndex + 1, item)
                                                                    dragOffset -= step
                                                                    view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                                                                } else if (dragOffset < -threshold && currentIndex > 0) {
                                                                    val item = reorderableKeys.removeAt(currentIndex)
                                                                    reorderableKeys.add(currentIndex - 1, item)
                                                                    dragOffset += step
                                                                    view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                                                                }
                                                            }
                                                        )
                                                    },
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Menu,
                                                    contentDescription = "Drag to reorder",
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f),
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                            Icon(
                                                imageVector = featureIcon,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    },
                                    trailingContent = {
                                        Switch(
                                            checked = isFeatureChecked,
                                            onCheckedChange = { checked ->
                                                viewModel.updateSettings { s ->
                                                    when (currentKey) {
                                                        "CALL" -> s.copy(showCalls = checked)
                                                        "MEDIA" -> s.copy(showMedia = checked)
                                                        "NOTIFICATION" -> s.copy(showNotifications = checked)
                                                        "PROGRESS" -> s.copy(showLiveProgress = checked)
                                                        "RECORDING" -> s.copy(showScreenRecording = checked)
                                                        "FLASHLIGHT" -> s.copy(showFlashlight = checked)
                                                        "FPS" -> s.copy(showFps = checked)
                                                        "WEATHER" -> s.copy(showWeather = checked)
                                                        "NETWORK_SPEED" -> s.copy(showNetworkSpeed = checked)
                                                        "PERSISTENT_CHARGING" -> s.copy(persistentCharging = checked)
                                                        "ALARM" -> s.copy(showAlarm = checked)
                                                        else -> s
                                                    }
                                                }
                                            }
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            item {
                SectionHeader(title = "Notification Island", icon = Icons.Default.Notifications)
            }
            item {
                SettingsCardItem(position = GroupPosition.SINGLE, isActive = settings.groupIslandNotifications) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text("Group notifications") },
                        supportingContent = { Text("Keep Telegram notifications in the island until dismissed, even without music or Incoming Notifications.") },
                        trailingContent = {
                            Switch(checked = settings.groupIslandNotifications, onCheckedChange = { enabled ->
                                viewModel.updateSettings { it.copy(groupIslandNotifications = enabled, showNotifications = if (enabled) true else it.showNotifications) }
                            })
                        }
                    )
                }
            }
            item {
                SettingsCardItem(position = GroupPosition.SINGLE) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text("Notification sources") },
                        supportingContent = { Text("Select apps allowed in the island") },
                        modifier = Modifier.clickable {
                            viewModel.loadInstalledApps(context)
                            showAppFilterSheet = true
                        }
                    )
                }
            }
            item {
                NotificationIconsOnlyControl()
            }

            // SECTION 5B: SYSTEM ALERTS & INDICATORS (ALWAYS TOP PRIORITY)
            item {
                SectionHeader(
                    title = "System Alerts",
                    icon = Icons.Default.NotificationsActive
                )
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP, isActive = settings.showBluetooth) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Bluetooth Connecting", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.Bluetooth, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showBluetooth,
                                    onCheckedChange = { show ->
                                        viewModel.updateSettings { it.copy(showBluetooth = show) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.showCharging) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Charging Indicator", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.Bolt, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showCharging,
                                    onCheckedChange = { show ->
                                        viewModel.updateSettings { it.copy(showCharging = show) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.showRingerMode) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Ring Modes", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showRingerMode,
                                    onCheckedChange = { show ->
                                        viewModel.updateSettings { it.copy(showRingerMode = show) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.showAirplaneMode) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Airplane Mode", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.AirplanemodeActive, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showAirplaneMode,
                                    onCheckedChange = { show ->
                                        viewModel.updateSettings { it.copy(showAirplaneMode = show) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM, isActive = settings.showClipboard) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Clipboard", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.ContentPaste, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showClipboard,
                                    onCheckedChange = { show ->
                                        viewModel.updateSettings { it.copy(showClipboard = show) }
                                    }
                                )
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        }
    }
        }
    }
}

    // App Filter Modal Bottom Sheet
    if (showAppFilterSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val coroutineScope = rememberCoroutineScope()

        ModalBottomSheet(
            onDismissRequest = { showAppFilterSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Per-App Notification Filter",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = appFilterQuery,
                    onValueChange = { appFilterQuery = it },
                    placeholder = { Text("Search installed apps...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                val filteredApps = remember(installedApps, appFilterQuery) {
                    if (appFilterQuery.isBlank()) installedApps
                    else installedApps.filter {
                        it.appName.contains(appFilterQuery, ignoreCase = true) ||
                                it.packageName.contains(appFilterQuery, ignoreCase = true)
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    items(filteredApps, key = { it.packageName }) { app ->
                        ListItem(
                            headlineContent = { Text(app.appName, fontWeight = FontWeight.Medium) },
                            leadingContent = {
                                if (app.icon != null) {
                                    val bitmap = remember(app.icon) { MostUsedAppsHelper.drawableToBitmap(app.icon) }
                                    if (bitmap != null) {
                                        Image(
                                            bitmap = bitmap.asImageBitmap(),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                        )
                                    } else {
                                        Icon(Icons.Default.Apps, contentDescription = null, modifier = Modifier.size(36.dp))
                                    }
                                } else {
                                    Icon(Icons.Default.Apps, contentDescription = null, modifier = Modifier.size(36.dp))
                                }
                            },
                            trailingContent = {
                                Switch(
                                    checked = app.packageName !in settings.blockedPackages,
                                    onCheckedChange = {
                                        viewModel.toggleAppBlocked(app.packageName)
                                    }
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    // Update Available Pop-up Shelf / Dialog
    if (showUpdateSheet) {
        val currentUpdate = (updateState as? MainScreenViewModel.UpdateState.Available)?.release
            ?: (updateState as? MainScreenViewModel.UpdateState.Downloading)?.release
        val downloadingProgress = (updateState as? MainScreenViewModel.UpdateState.Downloading)?.progress

        if (currentUpdate != null) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.dismissUpdateSheet() },
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Version ${currentUpdate.version}",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold
                                )
                                if (currentUpdate.isBeta) {
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = MaterialTheme.colorScheme.tertiaryContainer
                                    ) {
                                        Text(
                                            text = "BETA",
                                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = currentUpdate.name.takeIf { it != currentUpdate.version && it != currentUpdate.tagName } ?: "New GitHub Release",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (currentUpdate.changelog.isNotBlank()) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surfaceContainer
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "What's New",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = currentUpdate.changelog,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    if (downloadingProgress != null) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Downloading APK...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text("${(downloadingProgress * 100).roundToInt()}%", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            LinearProgressIndicator(
                                progress = { downloadingProgress },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.dismissUpdateSheet() },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Text("Later")
                        }

                        Button(
                            onClick = {
                                viewModel.downloadAndInstallUpdate(context, currentUpdate)
                            },
                            enabled = downloadingProgress == null,
                            modifier = Modifier.weight(1.5f),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            if (downloadingProgress != null) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    strokeWidth = 2.dp,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Downloading...")
                            } else {
                                Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Install Update")
                            }
                        }
                    }
                }
            }
        }
    }

    // Feedback Bottom Sheet Dialog
    if (showFeedbackSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFeedbackSheet = false },
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 4.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Header
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "Send Feedback",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Report a bug or share your feature ideas for Droidtools.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Category Selection: Bug Report vs Feature Suggestion
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = feedbackType == FeedbackType.BUG,
                        onClick = { feedbackType = FeedbackType.BUG },
                        label = { Text("Bug Report", fontWeight = FontWeight.SemiBold) },
                        leadingIcon = {
                            Icon(
                                Icons.Default.BugReport,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                    FilterChip(
                        selected = feedbackType == FeedbackType.FEATURE,
                        onClick = { feedbackType = FeedbackType.FEATURE },
                        label = { Text("Feature Idea", fontWeight = FontWeight.SemiBold) },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lightbulb,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Subject / Title input
                OutlinedTextField(
                    value = feedbackTitle,
                    onValueChange = { feedbackTitle = it },
                    label = { Text("Subject") },
                    placeholder = {
                        Text(
                            if (feedbackType == FeedbackType.BUG) "e.g. Media progress slider glitch"
                            else "e.g. Add lock screen island support"
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                // Detailed Description input
                OutlinedTextField(
                    value = feedbackDescription,
                    onValueChange = { feedbackDescription = it },
                    label = { Text("Description *") },
                    placeholder = {
                        Text(
                            if (feedbackType == FeedbackType.BUG) "Describe what happened and how to reproduce it..."
                            else "Describe your idea and how it should work..."
                        )
                    },
                    minLines = 4,
                    maxLines = 7,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                // Attachment Section (optional)
                if (attachmentUri == null) {
                    OutlinedButton(
                        onClick = { filePickerLauncher.launch("*/*") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.AttachFile, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add Attachment (Screenshot / Log)", fontSize = 13.sp)
                    }
                } else {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceContainer
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.AttachFile,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = attachmentName ?: "Attached file",
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(
                                onClick = {
                                    attachmentUri = null
                                    attachmentName = null
                                },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "Remove attachment",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }

                // Buttons: Cancel & Send Email
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { showFeedbackSheet = false },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            val subject = "[Droidtools ${if (feedbackType == FeedbackType.BUG) "Bug" else "Feature"}] ${feedbackTitle.ifBlank { if (feedbackType == FeedbackType.BUG) "Bug Report" else "Feature Suggestion" }}"
                            val body = buildString {
                                appendLine("Type: ${if (feedbackType == FeedbackType.BUG) "Bug Report" else "Feature Suggestion"}")
                                if (feedbackTitle.isNotBlank()) {
                                    appendLine("Subject: $feedbackTitle")
                                }
                                appendLine()
                                appendLine("Description:")
                                appendLine(feedbackDescription.trim())
                                appendLine()
                                appendLine("----------------------------------------")
                                appendLine("Device & App Info:")
                                appendLine("Droidtools Version: ${com.droidbubble.app.util.UpdateManager.getCurrentVersion(context)} (${context.packageName})")
                                appendLine("Device: ${Build.MANUFACTURER} ${Build.MODEL} (${Build.DEVICE})")
                                appendLine("Android Version: ${Build.VERSION.RELEASE} (SDK ${Build.VERSION.SDK_INT})")
                                appendLine("Island Enabled: ${settings.isEnabled}")
                                appendLine("----------------------------------------")
                            }

                            val hasAttachment = attachmentUri != null
                            val emailIntent = if (hasAttachment) {
                                Intent(Intent.ACTION_SEND).apply {
                                    type = context.contentResolver.getType(attachmentUri!!) ?: "*/*"
                                    putExtra(Intent.EXTRA_EMAIL, arrayOf("velocityniele@gmail.com"))
                                    putExtra(Intent.EXTRA_SUBJECT, subject)
                                    putExtra(Intent.EXTRA_TEXT, body)
                                    putExtra(Intent.EXTRA_STREAM, attachmentUri)
                                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                }
                            } else {
                                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:velocityniele@gmail.com")).apply {
                                    putExtra(Intent.EXTRA_EMAIL, arrayOf("velocityniele@gmail.com"))
                                    putExtra(Intent.EXTRA_SUBJECT, subject)
                                    putExtra(Intent.EXTRA_TEXT, body)
                                }
                            }

                            // Copy details to clipboard as backup in case user wants to paste elsewhere
                            try {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
                                clipboard?.setPrimaryClip(ClipData.newPlainText("Droidtools Feedback", body))
                            } catch (_: Exception) {}

                            val gmailIntent = Intent(emailIntent).setPackage("com.google.android.gm").apply {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                if (hasAttachment) {
                                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                }
                            }

                            try {
                                if (context.packageManager.resolveActivity(gmailIntent, 0) != null) {
                                    context.startActivity(gmailIntent)
                                    Toast.makeText(context, "Opening Gmail to send to velocityniele@gmail.com", Toast.LENGTH_SHORT).show()
                                } else {
                                    val chooser = Intent.createChooser(emailIntent, "Send Feedback via Email").apply {
                                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        if (hasAttachment) {
                                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                        }
                                    }
                                    context.startActivity(chooser)
                                    Toast.makeText(context, "Choose email client to send to velocityniele@gmail.com", Toast.LENGTH_SHORT).show()
                                }
                            } catch (_: Exception) {
                                val fallback = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:velocityniele@gmail.com?subject=" + Uri.encode(subject) + "&body=" + Uri.encode(body))).apply {
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                }
                                runCatching {
                                    context.startActivity(fallback)
                                    Toast.makeText(context, "Opening mail client...", Toast.LENGTH_SHORT).show()
                                }
                            }

                            feedbackTitle = ""
                            feedbackDescription = ""
                            attachmentUri = null
                            attachmentName = null
                            showFeedbackSheet = false
                        },
                        enabled = feedbackDescription.isNotBlank(),
                        modifier = Modifier.weight(1.5f),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Send Email")
                    }
                }
            }
        }
    }


}

private const val PROGRESSIVE_TOP_BLUR_AGSL = """
    uniform shader content;
    uniform float uStartY;
    uniform float uEndY;
    uniform float uMaxRadius;

    half4 main(float2 coord) {
        if (coord.y >= uEndY) {
            return content.eval(coord);
        }
        float t = clamp((uEndY - coord.y) / max(1.0, uEndY - uStartY), 0.0, 1.0);
        float radius = t * t * uMaxRadius;
        if (radius < 0.5) {
            return content.eval(coord);
        }
        half4 sum = content.eval(coord) * 0.20;
        float r1 = radius * 0.45;
        float r2 = radius;
        sum += content.eval(coord + float2(r1, 0.0)) * 0.12;
        sum += content.eval(coord + float2(-r1, 0.0)) * 0.12;
        sum += content.eval(coord + float2(0.0, r1)) * 0.12;
        sum += content.eval(coord + float2(0.0, -r1)) * 0.12;
        sum += content.eval(coord + float2(r2 * 0.707, r2 * 0.707)) * 0.08;
        sum += content.eval(coord + float2(-r2 * 0.707, r2 * 0.707)) * 0.08;
        sum += content.eval(coord + float2(r2 * 0.707, -r2 * 0.707)) * 0.08;
        sum += content.eval(coord + float2(-r2 * 0.707, -r2 * 0.707)) * 0.08;
        return sum;
    }
"""

@Composable
fun ProgressiveTopBlurBox(
    isScrolled: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    val density = LocalDensity.current
    val blurAlpha by animateFloatAsState(
        targetValue = if (isScrolled) 1f else 0f,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "topBlurAlpha"
    )
    val blurRegionPx = with(density) { 38.dp.toPx() }
    val maxSigmaPx = with(density) { 12.dp.toPx() } * blurAlpha
    val bgColor = MaterialTheme.colorScheme.background

    val topBlurModifier = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && blurAlpha > 0.02f) {
        Modifier.graphicsLayer {
            clip = true
            val shader = android.graphics.RuntimeShader(PROGRESSIVE_TOP_BLUR_AGSL).apply {
                setFloatUniform("uStartY", 0f)
                setFloatUniform("uEndY", blurRegionPx)
                setFloatUniform("uMaxRadius", maxSigmaPx)
            }
            renderEffect = android.graphics.RenderEffect
                .createRuntimeShaderEffect(shader, "content")
                .asComposeRenderEffect()
        }
    } else {
        Modifier
    }

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(topBlurModifier),
            content = content
        )
        if (blurAlpha > 0.01f) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .height(34.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                bgColor.copy(alpha = 0.88f * blurAlpha),
                                bgColor.copy(alpha = 0.45f * blurAlpha),
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}

private const val PROGRESSIVE_BOTTOM_BLUR_AGSL = """
    uniform shader content;
    uniform float uStartY;
    uniform float uEndY;
    uniform float uMaxRadius;

    half4 main(float2 coord) {
        if (coord.y <= uStartY) {
            return content.eval(coord);
        }
        float t = clamp((coord.y - uStartY) / max(1.0, uEndY - uStartY), 0.0, 1.0);
        float radius = t * t * uMaxRadius;
        if (radius < 0.5) {
            return content.eval(coord);
        }
        half4 sum = content.eval(coord) * 0.20;
        float r1 = radius * 0.45;
        float r2 = radius;
        sum += content.eval(coord + float2(r1, 0.0)) * 0.12;
        sum += content.eval(coord + float2(-r1, 0.0)) * 0.12;
        sum += content.eval(coord + float2(0.0, r1)) * 0.12;
        sum += content.eval(coord + float2(0.0, -r1)) * 0.12;
        sum += content.eval(coord + float2(r2 * 0.707, r2 * 0.707)) * 0.08;
        sum += content.eval(coord + float2(-r2 * 0.707, r2 * 0.707)) * 0.08;
        sum += content.eval(coord + float2(r2 * 0.707, -r2 * 0.707)) * 0.08;
        sum += content.eval(coord + float2(-r2 * 0.707, -r2 * 0.707)) * 0.08;
        return sum;
    }
"""

@Composable
fun CookieIconBox(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String? = null,
    isChecked: Boolean = false,
    modifier: Modifier = Modifier
) {
    CookieIconBox(
        painter = rememberVectorPainter(icon),
        contentDescription = contentDescription,
        isChecked = isChecked,
        modifier = modifier
    )
}

@Composable
fun CookieIconBox(
    painter: Painter,
    contentDescription: String? = null,
    isChecked: Boolean = false,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val containerColor = lerp(
        MaterialTheme.colorScheme.primaryContainer,
        Color.Black,
        if (isDark) 0.48f else 0.18f
    )
    val iconTint = if (isDark) {
        lerp(MaterialTheme.colorScheme.primary, Color.White, 0.22f)
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }
    val cookieRotation by animateFloatAsState(
        targetValue = if (isChecked) 45f else 0f,
        animationSpec = spring(
            dampingRatio = 0.45f,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "cookieRotation"
    )
    Box(
        modifier = modifier.size(38.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { rotationZ = cookieRotation },
            shape = M3Cookie6Shape,
            color = containerColor
        ) {}
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(20.dp),
            tint = iconTint
        )
    }
}

@Composable
fun ShortFloatingBottomNav(
    selectedTab: Int,
    onSelectTab: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // Very light colored background that matches the system accent
    val veryLightBarBg = lerp(Color(0xFFFAFAFC), MaterialTheme.colorScheme.primaryContainer, 0.16f)
    val veryLightBorder = lerp(Color(0xFFE4E4E9), MaterialTheme.colorScheme.primary, 0.14f)
    // Dark circle for the selected tab, with a light icon inside
    val selectedCircleBg = lerp(Color(0xFF18181B), MaterialTheme.colorScheme.primary, 0.16f)
    val selectedIconLight = lerp(Color(0xFFFAFAFC), MaterialTheme.colorScheme.primaryContainer, 0.22f)
    val unselectedIconDark = lerp(Color(0xFF52525B), MaterialTheme.colorScheme.onSurface, 0.18f)

    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = veryLightBarBg,
        tonalElevation = 0.dp,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, veryLightBorder)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val tabs = listOf(
                Triple(0, Icons.Default.Settings, "Droidtools"),
                Triple(1, Icons.Default.Smartphone, "Device Info"),
                Triple(2, Icons.Default.CloudDownload, "GitHub App Updates")
            )
            tabs.forEach { (idx, icon, label) ->
                val isSelected = selectedTab == idx
                val itemBg by animateColorAsState(
                    targetValue = if (isSelected) selectedCircleBg else Color.Transparent,
                    label = "floatingNavBg"
                )
                val itemFg by animateColorAsState(
                    targetValue = if (isSelected) selectedIconLight else unselectedIconDark,
                    label = "floatingNavFg"
                )
                Surface(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable { onSelectTab(idx) },
                    shape = CircleShape,
                    color = itemBg,
                    contentColor = itemFg
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = label,
                            tint = itemFg,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    badge: String? = null,
    badgeColor: Color = MaterialTheme.colorScheme.primary,
    actionLabel: String? = null,
    actionIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    onActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 2.dp, start = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f, fill = false)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        if (onActionClick != null && actionLabel != null) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(onClick = onActionClick)
                    .padding(horizontal = 8.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (actionIcon != null) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Text(
                    text = actionLabel,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else if (badge != null) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(badgeColor.copy(alpha = 0.2f))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = badge,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = badgeColor
                )
            }
        }
    }
}

enum class GroupPosition {
    TOP,
    MIDDLE,
    BOTTOM,
    SINGLE
}

fun getGroupedShape(position: GroupPosition): RoundedCornerShape {
    val outerRadius = 22.dp
    val innerRadius = 4.dp
    return when (position) {
        GroupPosition.TOP -> RoundedCornerShape(
            topStart = outerRadius,
            topEnd = outerRadius,
            bottomStart = innerRadius,
            bottomEnd = innerRadius
        )
        GroupPosition.MIDDLE -> RoundedCornerShape(innerRadius)
        GroupPosition.BOTTOM -> RoundedCornerShape(
            topStart = innerRadius,
            topEnd = innerRadius,
            bottomStart = outerRadius,
            bottomEnd = outerRadius
        )
        GroupPosition.SINGLE -> RoundedCornerShape(outerRadius)
    }
}

fun getHorizontalSegmentShape(index: Int, total: Int, isSelected: Boolean): RoundedCornerShape {
    val outerRadius = 22.dp
    val innerRadius = 4.dp
    return if (isSelected) {
        RoundedCornerShape(outerRadius)
    } else {
        when {
            index == 0 -> RoundedCornerShape(
                topStart = outerRadius,
                bottomStart = outerRadius,
                topEnd = innerRadius,
                bottomEnd = innerRadius
            )
            index == total - 1 -> RoundedCornerShape(
                topStart = innerRadius,
                bottomStart = innerRadius,
                topEnd = outerRadius,
                bottomEnd = outerRadius
            )
            else -> RoundedCornerShape(innerRadius)
        }
    }
}

@Composable
private fun NotificationIconsOnlyControl() {
    val context = LocalContext.current
    val helper = com.droidbubble.app.util.ShizukuHelper
    val mode = com.droidbubble.app.service.TwoIconStatusBar.Companion
    var enabled by remember { mutableStateOf(mode.enabled(context)) }
    var busy by remember { mutableStateOf(false) }
    fun changeMode(next: Boolean) {
        if (next) {
            if (!helper.isShizukuAvailable()) {
                Toast.makeText(context, "Start Shizuku first", Toast.LENGTH_SHORT).show()
                return
            }
            if (!helper.hasShizukuPermission()) {
                helper.requestShizukuPermission()
                Toast.makeText(context, "Allow Shizuku, then tap again", Toast.LENGTH_LONG).show()
                return
            }
            if (helper.isSystemStatusBarIconsHidden(context)) {
                Toast.makeText(context, "Turn off full status bar hiding first", Toast.LENGTH_LONG).show()
                return
            }
            if (com.droidbubble.app.service.IslandAccessibilityService.instance == null ||
                com.droidbubble.app.service.NotificationReceiverService.instance == null) {
                Toast.makeText(context, "Enable Accessibility and Notification access first", Toast.LENGTH_LONG).show()
                return
            }
            mode.setEnabled(context, true)
            enabled = true
            Toast.makeText(context, "Experimental mode enabled — check the status bar", Toast.LENGTH_LONG).show()
        } else {
            mode.setEnabled(context, false)
            enabled = false
            busy = true
            mode.restore(context) { ok ->
                busy = false
                Toast.makeText(context, if (ok) "Restore command sent" else "Restore failed — check Shizuku and try again", Toast.LENGTH_LONG).show()
            }
        }
    }
    SettingsCardItem(position = GroupPosition.SINGLE) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Text("Max 2 notification icons (experimental)", fontWeight = FontWeight.SemiBold)
            Text("Replaces the native notification icon strip with up to two recent app icons while Droidbubble runs. Notifications remain in the shade. Nothing OS may ignore the hide command.", style = MaterialTheme.typography.bodySmall)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = { changeMode(true) }, enabled = !busy && !enabled) { Text("Max 2 icons") }
                TextButton(onClick = { changeMode(false) }, enabled = !busy) { Text("Restore icons") }
            }
        }
    }
}

@Composable
fun SettingsCardItem(
    position: GroupPosition,
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    content: @Composable () -> Unit
) {
    val effectiveShape = if (isActive) {
        RoundedCornerShape(20.dp)
    } else {
        getGroupedShape(position)
    }

    val animatedColor by animateColorAsState(
        targetValue = if (isActive) {
            MaterialTheme.colorScheme.surfaceContainerHighest
        } else {
            MaterialTheme.colorScheme.surfaceContainerLow
        },
        label = "itemBgColor"
    )

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = effectiveShape,
        color = animatedColor,
        tonalElevation = if (isActive) 3.dp else 1.dp
    ) {
        content()
    }
}

@Composable
fun SliderSettingItem(
    label: String,
    value: Float,
    unit: String,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    onValueChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "${if (unit == "x" || unit == "dp") String.format(Locale.US, "%.1f", value).replace(".0", "") else value.toInt()} $unit",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            steps = steps,
            modifier = Modifier.height(24.dp)
        )
    }
}

enum class FeedbackType {
    BUG,
    FEATURE
}

private fun getAttachmentFileName(context: Context, uri: Uri): String {
    var name = "attachment"
    try {
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst() && nameIndex >= 0) {
                name = cursor.getString(nameIndex)
            }
        }
    } catch (_: Exception) {
    }
    return name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DimensionsPage(
    settings: IslandSettings,
    onUpdateSettings: ((IslandSettings) -> IslandSettings) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Island Dimensions", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        val pageListState = rememberLazyListState()
        val pageIsScrolled by remember {
            derivedStateOf {
                pageListState.firstVisibleItemIndex > 0 || pageListState.firstVisibleItemScrollOffset > 6
            }
        }
        ProgressiveTopBlurBox(
            isScrolled = pageIsScrolled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
        LazyColumn(
            state = pageListState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                val currentCutoutMode = when {
                    settings.isTeardropCutout -> CutoutModeOption.TEARDROP
                    settings.isLeftCutout -> CutoutModeOption.LEFT
                    settings.isRightCutout -> CutoutModeOption.RIGHT
                    else -> CutoutModeOption.CENTER
                }
                CutoutPositionSelectorCard(
                    selectedMode = currentCutoutMode,
                    onSelectMode = { mode ->
                        onUpdateSettings { s ->
                            when (mode) {
                                CutoutModeOption.CENTER -> s.copy(isLeftCutout = false, isRightCutout = false, isTeardropCutout = false)
                                CutoutModeOption.LEFT -> s.copy(isLeftCutout = true, isRightCutout = false, isTeardropCutout = false)
                                CutoutModeOption.RIGHT -> s.copy(isLeftCutout = false, isRightCutout = true, isTeardropCutout = false)
                                CutoutModeOption.TEARDROP -> s.copy(isLeftCutout = false, isRightCutout = false, isTeardropCutout = true)
                            }
                        }
                    }
                )
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP) {
                        SliderSettingItem(
                            label = "Width",
                            value = settings.widthDp.toFloat(),
                            unit = "dp",
                            valueRange = 50f..260f,
                            onValueChange = { onUpdateSettings { s -> s.copy(widthDp = it.roundToInt()) } }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        SliderSettingItem(
                            label = "Height",
                            value = settings.heightDp.toFloat(),
                            unit = "dp",
                            valueRange = 12f..56f,
                            onValueChange = { onUpdateSettings { s -> s.copy(heightDp = it.roundToInt()) } }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        SliderSettingItem(
                            label = "Corner Radius",
                            value = settings.cornerRadiusDp.toFloat(),
                            unit = "dp",
                            valueRange = 10f..28f,
                            onValueChange = { onUpdateSettings { s -> s.copy(cornerRadiusDp = it.roundToInt()) } }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        SliderSettingItem(
                            label = "Top Offset (Notch Line-up)",
                            value = settings.topOffsetDp.toFloat(),
                            unit = "dp",
                            valueRange = 0f..60f,
                            onValueChange = { onUpdateSettings { s -> s.copy(topOffsetDp = it.roundToInt()) } }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        SliderSettingItem(
                            label = "Horizontal Offset",
                            value = settings.horizontalOffsetDp.toFloat(),
                            unit = "dp",
                            valueRange = -50f..50f,
                            onValueChange = { onUpdateSettings { s -> s.copy(horizontalOffsetDp = it.roundToInt()) } }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        SliderSettingItem(
                            label = "Expanded Card Scale",
                            value = settings.expandedScale,
                            unit = "x",
                            valueRange = 0.8f..1.3f,
                            steps = 5,
                            onValueChange = { onUpdateSettings { s -> s.copy(expandedScale = (it * 10).roundToInt() / 10f) } }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM) {
                        SliderSettingItem(
                            label = "Animation Speed",
                            value = settings.animationSpeed,
                            unit = "x",
                            valueRange = 0.5f..2.0f,
                            steps = 14,
                            onValueChange = { onUpdateSettings { s -> s.copy(animationSpeed = (it * 10).roundToInt() / 10f) } }
                        )
                    }
                }
            }
        }
        }
    }
}

enum class CutoutModeOption {
    CENTER,
    LEFT,
    RIGHT,
    TEARDROP
}

@Composable
fun CutoutPositionSelectorCard(
    selectedMode: CutoutModeOption,
    onSelectMode: (CutoutModeOption) -> Unit
) {
    SettingsCardItem(position = GroupPosition.SINGLE) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Camera Cut-out Position",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CutoutPositionOptionButton(
                    label = "Center",
                    mode = CutoutModeOption.CENTER,
                    isSelected = selectedMode == CutoutModeOption.CENTER,
                    onClick = { onSelectMode(CutoutModeOption.CENTER) },
                    modifier = Modifier.weight(1f)
                )
                CutoutPositionOptionButton(
                    label = "Left",
                    mode = CutoutModeOption.LEFT,
                    isSelected = selectedMode == CutoutModeOption.LEFT,
                    onClick = { onSelectMode(CutoutModeOption.LEFT) },
                    modifier = Modifier.weight(1f)
                )
                CutoutPositionOptionButton(
                    label = "Right",
                    mode = CutoutModeOption.RIGHT,
                    isSelected = selectedMode == CutoutModeOption.RIGHT,
                    onClick = { onSelectMode(CutoutModeOption.RIGHT) },
                    modifier = Modifier.weight(1f)
                )
                CutoutPositionOptionButton(
                    label = "Tear-drop",
                    mode = CutoutModeOption.TEARDROP,
                    isSelected = selectedMode == CutoutModeOption.TEARDROP,
                    onClick = { onSelectMode(CutoutModeOption.TEARDROP) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun CutoutPositionOptionButton(
    label: String,
    mode: CutoutModeOption,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.72f)
        } else {
            MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.42f)
        },
        label = "cutoutBtnBg"
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.onPrimaryContainer
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        label = "cutoutBtnContent"
    )
    val bezelStrokeColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.55f)
    }
    val cutoutDotColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f)
    }

    Surface(
        onClick = onClick,
        modifier = modifier.height(92.dp),
        shape = RoundedCornerShape(16.dp),
        color = containerColor,
        border = if (isSelected) {
            BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
        } else {
            null
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, bottom = 10.dp, start = 6.dp, end = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Minimalist top phone bezel glyph with cutout position
            androidx.compose.foundation.Canvas(
                modifier = Modifier
                    .width(64.dp)
                    .height(40.dp)
            ) {
                val strokePx = 1.7.dp.toPx()
                val cornerPx = 11.dp.toPx()
                val padX = 4.dp.toPx()
                val topY = 4.dp.toPx()
                val bottomY = size.height
                val leftX = padX
                val rightX = size.width - padX

                val bezelOutlinePath = androidx.compose.ui.graphics.Path().apply {
                    moveTo(leftX, bottomY)
                    lineTo(leftX, topY + cornerPx)
                    quadraticTo(leftX, topY, leftX + cornerPx, topY)
                    lineTo(rightX - cornerPx, topY)
                    quadraticTo(rightX, topY, rightX, topY + cornerPx)
                    lineTo(rightX, bottomY)
                }
                drawPath(
                    path = bezelOutlinePath,
                    color = bezelStrokeColor,
                    style = androidx.compose.ui.graphics.drawscope.Stroke(
                        width = strokePx,
                        cap = androidx.compose.ui.graphics.StrokeCap.Round,
                        join = androidx.compose.ui.graphics.StrokeJoin.Round
                    )
                )

                val centerX = size.width / 2f
                val holeY = topY + 10.dp.toPx()
                val holeRadius = 3.5.dp.toPx()
                when (mode) {
                    CutoutModeOption.CENTER -> {
                        drawCircle(
                            color = cutoutDotColor,
                            radius = holeRadius,
                            center = androidx.compose.ui.geometry.Offset(centerX, holeY)
                        )
                    }
                    CutoutModeOption.LEFT -> {
                        drawCircle(
                            color = cutoutDotColor,
                            radius = holeRadius,
                            center = androidx.compose.ui.geometry.Offset(leftX + 12.dp.toPx(), holeY)
                        )
                    }
                    CutoutModeOption.RIGHT -> {
                        drawCircle(
                            color = cutoutDotColor,
                            radius = holeRadius,
                            center = androidx.compose.ui.geometry.Offset(rightX - 12.dp.toPx(), holeY)
                        )
                    }
                    CutoutModeOption.TEARDROP -> {
                        val dropR = 6.dp.toPx()
                        val earR = 4.dp.toPx()
                        val dropBottomY = topY + dropR * 1.45f
                        val teardropPath = androidx.compose.ui.graphics.Path().apply {
                            moveTo(centerX - dropR - earR, topY)
                            quadraticTo(
                                centerX - dropR, topY,
                                centerX - dropR * 0.88f, topY + dropR * 0.55f
                            )
                            cubicTo(
                                centerX - dropR * 0.65f, dropBottomY,
                                centerX + dropR * 0.65f, dropBottomY,
                                centerX + dropR * 0.88f, topY + dropR * 0.55f
                            )
                            quadraticTo(
                                centerX + dropR, topY,
                                centerX + dropR + earR, topY
                            )
                            close()
                        }
                        drawPath(path = teardropPath, color = cutoutDotColor)
                    }
                }
            }

            Text(
                text = label,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                color = contentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppearancePage(
    settings: IslandSettings,
    onUpdateSettings: ((IslandSettings) -> IslandSettings) -> Unit,
    onNavigateToMediaPlayer: () -> Unit = {},
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)
    var showFontDialog by remember { mutableStateOf(false) }

    if (showFontDialog) {
        val fontSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { showFontDialog = false },
            sheetState = fontSheetState,
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Select Font",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                ISLAND_FONTS.forEach { fontOption ->
                    val isSelected = (settings.fontKey == fontOption.key) || (settings.fontKey.isBlank() && fontOption.key == "DEFAULT")
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                onUpdateSettings { it.copy(fontKey = fontOption.key) }
                                showFontDialog = false
                            },
                        shape = RoundedCornerShape(16.dp),
                        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f) else MaterialTheme.colorScheme.surfaceContainerHigh
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = fontOption.name,
                                fontFamily = fontOption.family,
                                fontSize = 16.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                            RadioButton(
                                selected = isSelected,
                                onClick = {
                                    onUpdateSettings { it.copy(fontKey = fontOption.key) }
                                    showFontDialog = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Appearance & Feedback", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        val pageListState = rememberLazyListState()
        val pageIsScrolled by remember {
            derivedStateOf {
                pageListState.firstVisibleItemIndex > 0 || pageListState.firstVisibleItemScrollOffset > 6
            }
        }
        ProgressiveTopBlurBox(
            isScrolled = pageIsScrolled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
        LazyColumn(
            state = pageListState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    val isGlass = settings.designLanguage == DesignLanguage.GLASS
                    SettingsCardItem(
                        position = if (isGlass) GroupPosition.TOP else GroupPosition.SINGLE,
                        isActive = false
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                        ) {
                            ListItem(
                                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                headlineContent = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Text("Design Language", fontWeight = FontWeight.SemiBold)
                                        if (settings.designLanguage == DesignLanguage.GLASS) {
                                            Surface(
                                                shape = RoundedCornerShape(6.dp),
                                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                                contentColor = MaterialTheme.colorScheme.primary
                                            ) {
                                                Text(
                                                    text = "Experimental",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                },
                                leadingContent = {
                                    Icon(Icons.Default.Palette, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            )

                            // 3-way Segmented Control (3 separate buttons with list-item style rounding)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                val languages = listOf(
                                    DesignLanguage.MATERIAL3,
                                    DesignLanguage.DROIDBUBBLE,
                                    DesignLanguage.GLASS
                                )
                                languages.forEachIndexed { index, lang ->
                                    val isSelected = settings.designLanguage == lang
                                    val shape = getHorizontalSegmentShape(index, languages.size, isSelected)
                                    val backgroundColor by animateColorAsState(
                                        targetValue = if (isSelected) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                                        },
                                        label = "lang_bg"
                                    )
                                    val contentColor by animateColorAsState(
                                        targetValue = if (isSelected) {
                                            MaterialTheme.colorScheme.onPrimary
                                        } else {
                                            MaterialTheme.colorScheme.onSurfaceVariant
                                        },
                                        label = "lang_text"
                                    )

                                    Surface(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(shape)
                                            .clickable {
                                                onUpdateSettings {
                                                    it.copy(
                                                        designLanguage = lang,
                                                        glassEnabled = (lang == DesignLanguage.GLASS),
                                                        showOutline = if (lang == DesignLanguage.MATERIAL3) false else it.showOutline
                                                    )
                                                }
                                            },
                                        shape = shape,
                                        color = backgroundColor
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 12.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = when (lang) {
                                                    DesignLanguage.DROIDBUBBLE -> "Droidbubble"
                                                    DesignLanguage.GLASS -> "Glass"
                                                    DesignLanguage.MATERIAL3 -> "Material 3"
                                                },
                                                style = MaterialTheme.typography.labelMedium,
                                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                                color = contentColor,
                                                maxLines = 1
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (isGlass) {
                        SettingsCardItem(position = GroupPosition.BOTTOM, isActive = false) {
                            SliderSettingItem(
                                label = "Opacity",
                                value = settings.glassOpacity.toFloat(),
                                unit = "%",
                                valueRange = 0f..100f,
                                onValueChange = { onUpdateSettings { s -> s.copy(glassOpacity = it.roundToInt()) } }
                            )
                        }
                    }
                }
            }

            // Font Picker moved directly below Design Language
            item {
                SettingsCardItem(position = GroupPosition.SINGLE) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier.clickable { showFontDialog = true },
                        headlineContent = { Text("Change Font", fontWeight = FontWeight.SemiBold) },
                        supportingContent = {
                            val currentFontName = remember(settings.fontKey) {
                                ISLAND_FONTS.firstOrNull { it.key == settings.fontKey }?.name ?: "System Default"
                            }
                            Text(currentFontName, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        },
                        leadingContent = {
                            CookieIconBox(icon = Icons.Default.FontDownload)
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "Change Font",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    )
                }
            }

            if (settings.designLanguage != DesignLanguage.MATERIAL3) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        val outlineActive = settings.showOutline
                        SettingsCardItem(
                            position = if (outlineActive) GroupPosition.TOP else GroupPosition.SINGLE,
                            isActive = outlineActive
                        ) {
                            ListItem(
                                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                headlineContent = {
                                    Text(
                                        "Island Outline",
                                        fontWeight = FontWeight.SemiBold
                                    )
                                },
                                leadingContent = {
                                    Icon(
                                        Icons.Default.CropSquare,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                },
                                trailingContent = {
                                    Switch(
                                        checked = settings.showOutline,
                                        onCheckedChange = { show ->
                                            onUpdateSettings { it.copy(showOutline = show) }
                                        }
                                    )
                                }
                            )
                        }

                        if (outlineActive) {
                            SettingsCardItem(position = GroupPosition.BOTTOM) {
                                SliderSettingItem(
                                    label = "Outline Thickness",
                                    value = settings.outlineThicknessDp,
                                    unit = "dp",
                                    valueRange = 0.5f..4.0f,
                                    onValueChange = { onUpdateSettings { s -> s.copy(outlineThicknessDp = (it * 10).roundToInt() / 10f) } }
                                )
                            }
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP, isActive = settings.bubbleEffectEnabled) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Bubble Effect", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.RoundedCorner, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.bubbleEffectEnabled,
                                    onCheckedChange = { enabled ->
                                        onUpdateSettings { it.copy(bubbleEffectEnabled = enabled) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.showDualActivityInCompact) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Dual Activity on Minimised Island", fontWeight = FontWeight.SemiBold) },
                            supportingContent = { Text("Shows secondary bubble; when off, shows only priority activity until expanded", style = MaterialTheme.typography.bodySmall) },
                            leadingContent = {
                                Icon(Icons.Default.Widgets, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showDualActivityInCompact,
                                    onCheckedChange = { showDual ->
                                        onUpdateSettings { it.copy(showDualActivityInCompact = showDual) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM, isActive = settings.alwaysShowPill) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Always Show Pill", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.FitScreen, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.alwaysShowPill,
                                    onCheckedChange = { alwaysShow ->
                                        onUpdateSettings { it.copy(alwaysShowPill = alwaysShow) }
                                    }
                                )
                            }
                        )
                    }
                }
            }

            item {
                SettingsCardItem(position = GroupPosition.SINGLE) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier.clickable { onNavigateToMediaPlayer() },
                        headlineContent = { Text("Media Player Customisation", fontWeight = FontWeight.SemiBold) },
                        supportingContent = { Text("Preview, controls alignment, layer order & animations", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant) },
                        leadingContent = {
                            CookieIconBox(icon = Icons.Default.MusicNote)
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "Open Media Player Customisation",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    )
                }
            }



            item {
                AnimationCurveEditor(settings = settings, onUpdateSettings = onUpdateSettings)
            }
        }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralPage(
    settings: IslandSettings,
    onUpdateSettings: ((IslandSettings) -> IslandSettings) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)
    var showWeatherDialog by remember { mutableStateOf(false) }
    var weatherCityInput by remember(settings.weatherCity) { mutableStateOf(settings.weatherCity) }

    if (showWeatherDialog) {
        val weatherSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { showWeatherDialog = false },
            sheetState = weatherSheetState,
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp)
                    .padding(bottom = 28.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Weather Location",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Enter a city or location name to show weather for (e.g. London, Paris, New York, Thessaloniki). Leave blank for automatic time-zone approximation.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                OutlinedTextField(
                    value = weatherCityInput,
                    onValueChange = { weatherCityInput = it },
                    label = { Text("City or Town") },
                    singleLine = true,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = { showWeatherDialog = false },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            onUpdateSettings { it.copy(weatherCity = weatherCityInput.trim()) }
                            showWeatherDialog = false
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text("Save", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("General", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        val pageListState = rememberLazyListState()
        val pageIsScrolled by remember {
            derivedStateOf {
                pageListState.firstVisibleItemIndex > 0 || pageListState.firstVisibleItemScrollOffset > 6
            }
        }
        ProgressiveTopBlurBox(
            isScrolled = pageIsScrolled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
        LazyColumn(
            state = pageListState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // SECTION: DISPLAY & BEHAVIOR
            item {
                SectionHeader(title = "Display & Behavior")
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP, isActive = settings.hideOnLockScreen) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Hide on Lock Screen", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.Lock, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.hideOnLockScreen,
                                    onCheckedChange = { hide ->
                                        onUpdateSettings { it.copy(hideOnLockScreen = hide) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.showInLandscape) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Show in Landscape", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.ScreenRotation, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showInLandscape,
                                    onCheckedChange = { inLandscape ->
                                        onUpdateSettings { it.copy(showInLandscape = inLandscape) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM, isActive = settings.swapActivitiesOnSwipe) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Swipe to Swap Activities", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.TouchApp, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.swapActivitiesOnSwipe,
                                    onCheckedChange = { swapEnabled ->
                                        onUpdateSettings { it.copy(swapActivitiesOnSwipe = swapEnabled) }
                                    }
                                )
                            }
                        )
                    }
                }
            }

            // SECTION: INTERACTION & FEEDBACK
            item {
                SectionHeader(title = "Interaction & Feedback")
            }

            item {
                SettingsCardItem(position = GroupPosition.SINGLE, isActive = settings.hapticsEnabled) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text("Haptic Feedback", fontWeight = FontWeight.SemiBold) },
                        leadingContent = {
                            Icon(Icons.Default.Vibration, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        },
                        trailingContent = {
                            Switch(
                                checked = settings.hapticsEnabled,
                                onCheckedChange = { haptics ->
                                        onUpdateSettings { it.copy(hapticsEnabled = haptics) }
                                }
                            )
                        }
                    )
                }
            }

            // SECTION: NOTIFICATIONS
            item {
                SectionHeader(title = "Notifications")
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP, isActive = settings.autoDismissNotifications) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Auto-Dismiss Notifications", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.Timer, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.autoDismissNotifications,
                                    onCheckedChange = { autoDismiss ->
                                        onUpdateSettings { it.copy(autoDismissNotifications = autoDismiss) }
                                    }
                                )
                            }
                        )
                    }

                    if (settings.autoDismissNotifications) {
                        SettingsCardItem(position = GroupPosition.MIDDLE) {
                            SliderSettingItem(
                                label = "Auto-Dismiss Duration",
                                value = settings.notificationAutoDismissSeconds.toFloat(),
                                unit = "s",
                                valueRange = 1f..10f,
                                steps = 8,
                                onValueChange = { seconds ->
                                    onUpdateSettings { it.copy(notificationAutoDismissSeconds = seconds.roundToInt().coerceIn(1, 10)) }
                                }
                            )
                        }
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM) {
                        SliderSettingItem(
                            label = "Notification Max Lines",
                            value = settings.notificationMaxLines.toFloat(),
                            unit = "lines",
                            valueRange = 1f..6f,
                            steps = 4,
                            onValueChange = { lines ->
                                onUpdateSettings { it.copy(notificationMaxLines = lines.roundToInt().coerceIn(1, 6)) }
                            }
                        )
                    }
                }
            }

            // SECTION: SERVICES & FEATURES
            item {
                SectionHeader(title = "Services & Features")
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP, isActive = settings.dismissMediaOnPause) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Dismiss Media on Pause", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.MusicNote, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.dismissMediaOnPause,
                                    onCheckedChange = { dismiss ->
                                        onUpdateSettings { it.copy(dismissMediaOnPause = dismiss) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            modifier = Modifier.clickable {
                                weatherCityInput = settings.weatherCity
                                showWeatherDialog = true
                            },
                            headlineContent = {
                                Text(
                                    if (settings.weatherCity.isBlank()) "Weather Location" else "Weather Location (${settings.weatherCity})",
                                    fontWeight = FontWeight.SemiBold
                                )
                            },
                            leadingContent = {
                                CookieIconBox(icon = Icons.Default.WbSunny)
                            },
                            trailingContent = {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForwardIos,
                                    contentDescription = "Set Location",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        )
                    }
                }
            }
        }
        }
    }
}


private fun springStep(damping: Float, stiffness: Float, t: Float): Float {
    val w = Math.sqrt(stiffness.toDouble())
    val z = damping.toDouble()
    return if (z < 1.0) {
        val wd = w * Math.sqrt(1.0 - z * z)
        (1.0 - Math.exp(-z * w * t) * (Math.cos(wd * t) + (z * w / wd) * Math.sin(wd * t))).toFloat()
    } else {
        (1.0 - Math.exp(-w * t) * (1.0 + w * t)).toFloat()
    }
}

@Composable
fun AnimationCurveEditor(
    settings: IslandSettings,
    onUpdateSettings: ((IslandSettings) -> IslandSettings) -> Unit
) {
    val targets = listOf("Expand", "Appear", "Split")
    var selected by remember { mutableStateOf(0) }
    val defaults = listOf(0.74f to 380f, 0.60f to 280f, 0.63f to 320f)
    val damping = when (selected) { 0 -> settings.expandDamping; 1 -> settings.appearDamping; else -> settings.splitDamping }
    val stiffness = when (selected) { 0 -> settings.expandStiffness; 1 -> settings.appearStiffness; else -> settings.splitStiffness }
    val isDefault = damping == defaults[selected].first && stiffness == defaults[selected].second

    fun setCurve(d: Float, k: Float) {
        onUpdateSettings {
            when (selected) {
                0 -> it.copy(expandDamping = d, expandStiffness = k)
                1 -> it.copy(appearDamping = d, appearStiffness = k)
                else -> it.copy(splitDamping = d, splitStiffness = k)
            }
        }
    }

    SettingsCardItem(position = GroupPosition.SINGLE) {
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Animation, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.width(16.dp))
                Text("Animation Curves", fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                TextButton(onClick = { setCurve(defaults[selected].first, defaults[selected].second) }, enabled = !isDefault) {
                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Reset")
                }
            }
            Spacer(Modifier.height(10.dp))

            // Expand / Appear / Split segmented buttons matching screen record & live progress cards
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                targets.forEachIndexed { i, label ->
                    val isSelected = selected == i
                    val shape = when (i) {
                        0 -> RoundedCornerShape(
                            topStart = 14.dp,
                            bottomStart = 14.dp,
                            topEnd = 4.dp,
                            bottomEnd = 4.dp
                        )
                        targets.size - 1 -> RoundedCornerShape(
                            topStart = 4.dp,
                            bottomStart = 4.dp,
                            topEnd = 14.dp,
                            bottomEnd = 14.dp
                        )
                        else -> RoundedCornerShape(4.dp)
                    }

                    FilledTonalButton(
                        onClick = { selected = i },
                        modifier = Modifier
                            .weight(1f)
                            .height(38.dp),
                        shape = shape,
                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.6f),
                            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text(
                            text = label,
                            fontSize = 13.5.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
            Spacer(Modifier.height(14.dp))

            val lineColor = MaterialTheme.colorScheme.primary
            val guideColor = MaterialTheme.colorScheme.outlineVariant
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.6f))
            ) {
                androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                    val padX = 14.dp.toPx()
                    val padY = 22.dp.toPx()
                    val w = size.width - padX * 2
                    val h = size.height - padY * 2
                    val maxV = 1.5f
                    val dash = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    val targetY = padY + h * (1f - 1f / maxV)
                    drawLine(guideColor, androidx.compose.ui.geometry.Offset(padX, targetY), androidx.compose.ui.geometry.Offset(padX + w, targetY), strokeWidth = 1.5f, pathEffect = dash)
                    val baseY = padY + h
                    drawLine(guideColor, androidx.compose.ui.geometry.Offset(padX, baseY), androidx.compose.ui.geometry.Offset(padX + w, baseY), strokeWidth = 1.5f)
                    val path = androidx.compose.ui.graphics.Path()
                    val n = 80
                    for (i in 0..n) {
                        val tt = i / n.toFloat()
                        val v = springStep(damping, stiffness, tt * 1.0f).coerceIn(0f, maxV)
                        val x = padX + w * tt
                        val y = padY + h * (1f - v / maxV)
                        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                    }
                    drawPath(
                        path, lineColor,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(
                            width = 3.dp.toPx(),
                            cap = androidx.compose.ui.graphics.StrokeCap.Round,
                            join = androidx.compose.ui.graphics.StrokeJoin.Round
                        )
                    )
                }
            }

            val bounce = ((1f - damping) / 0.7f).coerceIn(0f, 1f)
            SliderSettingItemPct(
                label = "Bounce",
                valueText = "${(bounce * 100).toInt()}%",
                value = bounce,
                valueRange = 0f..1f,
                onValueChange = { b -> setCurve((1f - 0.7f * b), stiffness) }
            )
            SliderSettingItemPct(
                label = "Snap",
                valueText = "${stiffness.toInt()}",
                value = stiffness,
                valueRange = 150f..700f,
                onValueChange = { k -> setCurve(damping, k.roundToInt().toFloat()) }
            )
        }
    }
}

@Composable
private fun SliderSettingItemPct(
    label: String,
    valueText: String,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(valueText, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
        }
        Slider(value = value, onValueChange = onValueChange, valueRange = valueRange, modifier = Modifier.height(28.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaPlayerCustomizationPage(
    settings: IslandSettings,
    onUpdateSettings: ((IslandSettings) -> IslandSettings) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)

    val activeMedia by IslandStateManager.activeMedia.collectAsState()
    val sampleArt = remember {
        val b = android.graphics.Bitmap.createBitmap(240, 240, android.graphics.Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(b)
        val paint = android.graphics.Paint()
        paint.shader = android.graphics.LinearGradient(
            0f, 0f, 240f, 240f,
            intArrayOf(
                android.graphics.Color.parseColor("#E91E63"),
                android.graphics.Color.parseColor("#9C27B0"),
                android.graphics.Color.parseColor("#3F51B5")
            ),
            null,
            android.graphics.Shader.TileMode.CLAMP
        )
        canvas.drawRect(0f, 0f, 240f, 240f, paint)
        b
    }
    val dummyMedia = remember(sampleArt) {
        MediaInfo(
            packageName = "com.spotify.music",
            title = "Starboy",
            artist = "The Weeknd, Daft Punk",
            isPlaying = true,
            durationMs = 230000L,
            positionMs = 85000L,
            albumArt = sampleArt
        )
    }
    val currentMedia = activeMedia ?: dummyMedia
    val mediaArt = currentMedia.albumArt ?: currentMedia.appIcon ?: sampleArt
    val mediaAccentColor = remember(mediaArt) { extractMediaAccentColor(mediaArt) }
    val blurredMediaArt = remember(mediaArt, settings.blurredAlbumArtBackground) {
        if (settings.blurredAlbumArtBackground && mediaArt != null) createBlurredBitmap(mediaArt) else null
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Media Player",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        val pageListState = rememberLazyListState()
        val pageIsScrolled by remember {
            derivedStateOf {
                pageListState.firstVisibleItemIndex > 0 || pageListState.firstVisibleItemScrollOffset > 6
            }
        }
        ProgressiveTopBlurBox(
            isScrolled = pageIsScrolled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
        LazyColumn(
            state = pageListState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // SECTION 1: PREVIEW
            item {
                SectionHeader(title = "Preview")
            }

            item {
                val previewShape = RoundedCornerShape(28.dp)
                val cardBg = when {
                    settings.isMaterial3 -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
                    settings.isGlass -> Color.Black.copy(alpha = (settings.glassOpacity / 100f).coerceIn(0.15f, 0.95f))
                    else -> Color(0xFF121214)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(188.dp)
                        .clip(previewShape)
                        .background(cardBg)
                ) {
                    if (settings.blurredAlbumArtBackground && mediaArt != null) {
                        Image(
                            bitmap = (blurredMediaArt ?: mediaArt).asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    scaleX = 1.35f
                                    scaleY = 1.35f
                                    alpha = 0.78f
                                }
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.38f))
                        )
                    }

                    if (settings.mediaWaveAnimation) {
                        MediaBottomWaveOverlay(
                            accentColor = if (mediaAccentColor != Color.Transparent) mediaAccentColor else Color(0xFF64D2FF),
                            isPlaying = currentMedia.isPlaying,
                            alphaMultiplier = 1f,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp, vertical = 18.dp)
                    ) {
                        ExpandedMediaView(
                            media = currentMedia,
                            onCollapse = {},
                            scale = 1.0f,
                            spinningAlbumArt = settings.spinningAlbumArt,
                            wavyProgressBar = settings.wavyProgressBar,
                            showMediaLyrics = settings.showMediaLyrics,
                            designLanguage = settings.designLanguage,
                            mediaControlAlignment = settings.mediaControlAlignment,
                            mediaLayersOrder = settings.mediaLayersOrder
                        )
                    }
                }
            }

            // SECTION 2: CONTROLS ALIGNMENT
            item {
                SectionHeader(title = "Controls Alignment")
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    val alignments = listOf(
                        "LEFT" to "Left",
                        "CENTER" to "Center",
                        "RIGHT" to "Right"
                    )
                    alignments.forEachIndexed { index, (key, label) ->
                        val isSelected = settings.mediaControlAlignment == key
                        val shape = getHorizontalSegmentShape(index, alignments.size, isSelected)
                        val backgroundColor by animateColorAsState(
                            targetValue = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            },
                            label = "align_bg"
                        )
                        val contentColor by animateColorAsState(
                            targetValue = if (isSelected) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            label = "align_text"
                        )

                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(shape)
                                .clickable {
                                    onUpdateSettings { it.copy(mediaControlAlignment = key) }
                                },
                            shape = shape,
                            color = backgroundColor
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = contentColor,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            // SECTION 3: LAYER ORDER
            item {
                SectionHeader(title = "Layer Order")
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    val currentOrder = if (settings.mediaLayersOrder.isNotEmpty()) settings.mediaLayersOrder else DEFAULT_MEDIA_LAYERS
                    currentOrder.forEachIndexed { index, layerKey ->
                        val position = when {
                            currentOrder.size == 1 -> GroupPosition.SINGLE
                            index == 0 -> GroupPosition.TOP
                            index == currentOrder.size - 1 -> GroupPosition.BOTTOM
                            else -> GroupPosition.MIDDLE
                        }

                        val (title, icon) = when (layerKey) {
                            "COVER_AND_TITLE" -> "Album cover and title" to Icons.Default.Album
                            "SLIDER" -> "Media slider" to Icons.Default.LinearScale
                            "CONTROLS" -> "Control buttons" to Icons.Default.PlayCircleOutline
                            else -> layerKey to Icons.Default.Layers
                        }

                        SettingsCardItem(position = position) {
                            ListItem(
                                colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                headlineContent = { Text(title, fontWeight = FontWeight.SemiBold) },
                                leadingContent = {
                                    CookieIconBox(icon = icon)
                                },
                                trailingContent = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        IconButton(
                                            onClick = {
                                                if (index > 0) {
                                                    val newOrder = currentOrder.toMutableList()
                                                    val temp = newOrder[index]
                                                    newOrder[index] = newOrder[index - 1]
                                                    newOrder[index - 1] = temp
                                                    onUpdateSettings { it.copy(mediaLayersOrder = newOrder) }
                                                }
                                            },
                                            enabled = index > 0
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowUp,
                                                contentDescription = "Move Up",
                                                tint = if (index > 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                                            )
                                        }

                                        IconButton(
                                            onClick = {
                                                if (index < currentOrder.size - 1) {
                                                    val newOrder = currentOrder.toMutableList()
                                                    val temp = newOrder[index]
                                                    newOrder[index] = newOrder[index + 1]
                                                    newOrder[index + 1] = temp
                                                    onUpdateSettings { it.copy(mediaLayersOrder = newOrder) }
                                                }
                                            },
                                            enabled = index < currentOrder.size - 1
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.KeyboardArrowDown,
                                                contentDescription = "Move Down",
                                                tint = if (index < currentOrder.size - 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // SECTION 4: OPTIONS & ANIMATIONS
            item {
                SectionHeader(title = "Options & Animations")
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP, isActive = settings.mediaWaveAnimation) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Ambient Album Wave", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.GraphicEq, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.mediaWaveAnimation,
                                    onCheckedChange = { enabled ->
                                        onUpdateSettings { it.copy(mediaWaveAnimation = enabled) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.wavyProgressBar) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Wavy Progress Scrubber", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.LinearScale, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.wavyProgressBar,
                                    onCheckedChange = { enabled ->
                                        onUpdateSettings { it.copy(wavyProgressBar = enabled) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.spinningAlbumArt) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Spinning Vinyl Cover", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.Album, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.spinningAlbumArt,
                                    onCheckedChange = { enabled ->
                                        onUpdateSettings { it.copy(spinningAlbumArt = enabled) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE, isActive = settings.showMediaLyrics) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("1-Line Karaoke Lyrics", fontWeight = FontWeight.SemiBold) },
                            supportingContent = { Text("Shows synced per-line lyrics when available", style = MaterialTheme.typography.bodySmall) },
                            leadingContent = {
                                Icon(Icons.Default.Subtitles, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.showMediaLyrics,
                                    onCheckedChange = { enabled ->
                                        onUpdateSettings { it.copy(showMediaLyrics = enabled) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM, isActive = settings.blurredAlbumArtBackground) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Blurred Album Art Background", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                Icon(Icons.Default.MusicNote, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.blurredAlbumArtBackground,
                                    onCheckedChange = { blurred ->
                                        onUpdateSettings { it.copy(blurredAlbumArtBackground = blurred) }
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DroidbarPage(
    settings: IslandSettings,
    onUpdateSettings: ((IslandSettings) -> IslandSettings) -> Unit,
    onOpenAppsConfig: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val tasksRepo = remember { com.droidbubble.app.tasks.TasksRepository.getInstance(context) }
    val allShortcuts by tasksRepo.shortcuts.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Droidbar", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        val pageListState = rememberLazyListState()
        val pageIsScrolled by remember {
            derivedStateOf {
                pageListState.firstVisibleItemIndex > 0 || pageListState.firstVisibleItemScrollOffset > 6
            }
        }
        ProgressiveTopBlurBox(
            isScrolled = pageIsScrolled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
        LazyColumn(
            state = pageListState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // MASTER ENABLE SWITCH
            item {
                SettingsCardItem(position = GroupPosition.SINGLE, isActive = settings.droidbarEnabled) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text("Enable Droidbar", fontWeight = FontWeight.SemiBold) },
                        leadingContent = {
                            CookieIconBox(
                                icon = Icons.Default.VerticalSplit,
                                isChecked = settings.droidbarEnabled
                            )
                        },
                        trailingContent = {
                            Switch(
                                checked = settings.droidbarEnabled,
                                onCheckedChange = { enabled ->
                                    onUpdateSettings { it.copy(droidbarEnabled = enabled) }
                                    if (enabled && PermissionHelper.isOverlayGranted(context)) {
                                        IslandOverlayService.start(context)
                                    }
                                }
                            )
                        }
                    )
                }
            }

            // CHEVRON TAB TO CONFIGURE APPS
            item {
                SettingsCardItem(position = GroupPosition.SINGLE) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text("Configure displayed apps", fontWeight = FontWeight.SemiBold) },
                        supportingContent = {
                            Text(
                                if (settings.droidbarUseMostUsedApps) "6 most used apps (auto)" else "Custom app selection",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        leadingContent = {
                            CookieIconBox(icon = Icons.Default.Apps)
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                contentDescription = "Configure",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        modifier = Modifier.clickable { onOpenAppsConfig() }
                    )
                }
            }

            // OVERLAY PERMISSION WARNING IF NEEDED
            if (!PermissionHelper.isOverlayGranted(context)) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f)
                    ) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Overlay Permission Required", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.error) },
                            supportingContent = { Text("Required to display the edge bar over other apps", style = MaterialTheme.typography.bodySmall) },
                            trailingContent = {
                                Button(
                                    onClick = {
                                        runCatching {
                                            context.startActivity(
                                                Intent(
                                                    android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                                    android.net.Uri.parse("package:${context.packageName}")
                                                ).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
                                            )
                                        }
                                    },
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("Grant", fontWeight = FontWeight.SemiBold)
                                }
                            }
                        )
                    }
                }
            }

            // CONTROLS SECTION (NO DESCRIPTIONS)
            item {
                SectionHeader(title = "Controls")
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Flashlight", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(
                                    icon = Icons.Default.FlashlightOn,
                                    isChecked = settings.droidbarShowFlashlight
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.droidbarShowFlashlight,
                                    onCheckedChange = { checked ->
                                        onUpdateSettings { it.copy(droidbarShowFlashlight = checked) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Ring mode", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(
                                    icon = Icons.Default.NotificationsActive,
                                    isChecked = settings.droidbarShowRingMode
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.droidbarShowRingMode,
                                    onCheckedChange = { checked ->
                                        onUpdateSettings { it.copy(droidbarShowRingMode = checked) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Screenshot", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(
                                    icon = Icons.Default.Screenshot,
                                    isChecked = settings.droidbarShowScreenshot
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.droidbarShowScreenshot,
                                    onCheckedChange = { checked ->
                                        onUpdateSettings { it.copy(droidbarShowScreenshot = checked) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Wi-Fi", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(
                                    icon = Icons.Default.Wifi,
                                    isChecked = settings.droidbarShowWifi
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.droidbarShowWifi,
                                    onCheckedChange = { checked ->
                                        onUpdateSettings { it.copy(droidbarShowWifi = checked) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Mobile data", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(
                                    icon = Icons.Default.SignalCellularAlt,
                                    isChecked = settings.droidbarShowMobileData
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.droidbarShowMobileData,
                                    onCheckedChange = { checked ->
                                        onUpdateSettings { it.copy(droidbarShowMobileData = checked) }
                                    }
                                )
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Assistant", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(
                                    painter = painterResource(R.drawable.ic_assistant_mic),
                                    isChecked = settings.droidbarShowAssistant
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.droidbarShowAssistant,
                                    onCheckedChange = { checked ->
                                        onUpdateSettings { it.copy(droidbarShowAssistant = checked) }
                                    }
                                )
                            }
                        )
                    }
                }
            }

            // TASK SHORTCUTS IN DROIDBAR SECTION
            if (allShortcuts.isNotEmpty()) {
                item {
                    SectionHeader(title = "Task Shortcuts", icon = Icons.Default.Bolt)
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        allShortcuts.forEachIndexed { index, shortcut ->
                            val pos = when {
                                allShortcuts.size == 1 -> GroupPosition.SINGLE
                                index == 0 -> GroupPosition.TOP
                                index == allShortcuts.lastIndex -> GroupPosition.BOTTOM
                                else -> GroupPosition.MIDDLE
                            }
                            val isAdded = shortcut.showInDroidbar || settings.droidbarShortcutIds.contains(shortcut.id)

                            SettingsCardItem(position = pos, isActive = isAdded) {
                                ListItem(
                                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                    headlineContent = { Text(shortcut.name, fontWeight = FontWeight.SemiBold) },
                                    leadingContent = {
                                        CookieIconBox(
                                            icon = com.droidbubble.app.tasks.TaskIcons.getIcon(shortcut.iconName),
                                            isChecked = isAdded
                                        )
                                    },
                                    trailingContent = {
                                        Switch(
                                            checked = isAdded,
                                            onCheckedChange = { checked ->
                                                tasksRepo.saveShortcut(shortcut.copy(showInDroidbar = checked))
                                                onUpdateSettings { current ->
                                                    val updatedIds = if (checked) {
                                                        (current.droidbarShortcutIds + shortcut.id).distinct()
                                                    } else {
                                                        current.droidbarShortcutIds - shortcut.id
                                                    }
                                                    current.copy(droidbarShortcutIds = updatedIds)
                                                }
                                            }
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // APPEARANCE & FEEDBACK SECTION (NO DESCRIPTIONS)
            item {
                SectionHeader(title = "Appearance & Feedback", icon = Icons.Default.Palette)
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    SettingsCardItem(position = GroupPosition.TOP) {
                        SliderSettingItem(
                            label = "Background opacity",
                            value = (settings.droidbarBackgroundOpacity * 100f).roundToInt().toFloat(),
                            unit = "%",
                            valueRange = 0f..100f,
                            onValueChange = { pct ->
                                onUpdateSettings { it.copy(droidbarBackgroundOpacity = (pct / 100f).coerceIn(0f, 1f)) }
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        SliderSettingItem(
                            label = "Max height",
                            value = settings.droidbarMaxHeightDp.toFloat(),
                            unit = "dp",
                            valueRange = 160f..600f,
                            onValueChange = { h ->
                                onUpdateSettings { it.copy(droidbarMaxHeightDp = h.roundToInt()) }
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.MIDDLE) {
                        SliderSettingItem(
                            label = "Y position",
                            value = settings.droidbarYOffsetDp.toFloat(),
                            unit = "dp",
                            valueRange = -250f..250f,
                            onValueChange = { y ->
                                onUpdateSettings { it.copy(droidbarYOffsetDp = y.roundToInt()) }
                            }
                        )
                    }

                    SettingsCardItem(position = GroupPosition.BOTTOM, isActive = settings.droidbarHapticsEnabled) {
                        ListItem(
                            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                            headlineContent = { Text("Haptic feedback", fontWeight = FontWeight.SemiBold) },
                            leadingContent = {
                                CookieIconBox(
                                    icon = Icons.Default.Vibration,
                                    isChecked = settings.droidbarHapticsEnabled
                                )
                            },
                            trailingContent = {
                                Switch(
                                    checked = settings.droidbarHapticsEnabled,
                                    onCheckedChange = { checked ->
                                        onUpdateSettings { it.copy(droidbarHapticsEnabled = checked) }
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DroidbarAppsConfigPage(
    settings: IslandSettings,
    onUpdateSettings: ((IslandSettings) -> IslandSettings) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)
    val context = LocalContext.current
    val tasksRepo = remember { com.droidbubble.app.tasks.TasksRepository.getInstance(context) }
    val allShortcuts by tasksRepo.shortcuts.collectAsState()
    var slotToEdit by remember { mutableIntStateOf(-1) }
    var showAppPicker by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Configure Apps", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        val pageListState = rememberLazyListState()
        val pageIsScrolled by remember {
            derivedStateOf {
                pageListState.firstVisibleItemIndex > 0 || pageListState.firstVisibleItemScrollOffset > 6
            }
        }
        ProgressiveTopBlurBox(
            isScrolled = pageIsScrolled,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
        LazyColumn(
            state = pageListState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // TOGGLE: USE MOST USED APPS
            item {
                SettingsCardItem(position = GroupPosition.SINGLE) {
                    ListItem(
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        headlineContent = { Text("Use most used apps", fontWeight = FontWeight.SemiBold) },
                        supportingContent = {
                            Text(
                                "Automatically display your 6 most frequently used apps in Droidbar",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        trailingContent = {
                            Switch(
                                checked = settings.droidbarUseMostUsedApps,
                                onCheckedChange = { checked ->
                                    onUpdateSettings { it.copy(droidbarUseMostUsedApps = checked) }
                                }
                            )
                        }
                    )
                }
            }

            // WHEN OFF: MANUALLY CHANGE EACH APP
            if (!settings.droidbarUseMostUsedApps) {
                item {
                    SectionHeader(title = "App Slots (1 - 6)")
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        val pm = context.packageManager
                        for (slotIndex in 0 until 6) {
                            val pkg = settings.droidbarCustomApps.getOrNull(slotIndex)
                            val appInfo = remember(pkg) {
                                if (!pkg.isNullOrBlank()) {
                                    runCatching {
                                        val ai = pm.getApplicationInfo(pkg, 0)
                                        BarAppInfo(
                                            packageName = pkg,
                                            label = pm.getApplicationLabel(ai).toString(),
                                            iconBitmap = MostUsedAppsHelper.drawableToBitmap(pm.getApplicationIcon(ai))
                                        )
                                    }.getOrNull()
                                } else null
                            }

                            val position = when (slotIndex) {
                                0 -> GroupPosition.TOP
                                5 -> GroupPosition.BOTTOM
                                else -> GroupPosition.MIDDLE
                            }

                            SettingsCardItem(position = position) {
                                ListItem(
                                    colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                                    headlineContent = {
                                        Text(
                                            text = appInfo?.label ?: "Slot ${slotIndex + 1}: Select App",
                                            fontWeight = FontWeight.SemiBold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    },
                                    supportingContent = {
                                        Text(
                                            text = if (appInfo != null) "Slot ${slotIndex + 1} β€Ά Tap to change" else "Slot ${slotIndex + 1} (Empty)",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    },
                                    leadingContent = {
                                        Surface(
                                            modifier = Modifier.size(38.dp),
                                            shape = RoundedCornerShape(12.dp),
                                            color = MaterialTheme.colorScheme.surfaceContainerHigh
                                        ) {
                                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                                if (appInfo?.iconBitmap != null) {
                                                    Image(
                                                        bitmap = appInfo.iconBitmap.asImageBitmap(),
                                                        contentDescription = appInfo.label,
                                                        modifier = Modifier.size(28.dp)
                                                    )
                                                } else {
                                                    Icon(
                                                        imageVector = Icons.Default.Add,
                                                        contentDescription = "Add",
                                                        tint = MaterialTheme.colorScheme.primary,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                }
                                            }
                                        }
                                    },
                                    trailingContent = {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                                            contentDescription = "Select",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    },
                                    modifier = Modifier.clickable {
                                        slotToEdit = slotIndex
                                        showAppPicker = true
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
        }
    }

    if (showAppPicker && slotToEdit >= 0) {
        AppPickerDialog(
            onDismiss = {
                showAppPicker = false
                slotToEdit = -1
            },
            onSelectApp = { selectedApp ->
                val current = settings.droidbarCustomApps.toMutableList()
                while (current.size <= slotToEdit) {
                    current.add("")
                }
                current[slotToEdit] = selectedApp.packageName
                onUpdateSettings { it.copy(droidbarCustomApps = current) }
                showAppPicker = false
                slotToEdit = -1
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPickerDialog(
    onDismiss: () -> Unit,
    onSelectApp: (BarAppInfo) -> Unit
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var allApps by remember { mutableStateOf<List<BarAppInfo>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val loaded = withContext(Dispatchers.IO) {
            MostUsedAppsHelper.getAllInstalledApps(context)
        }
        allApps = loaded
        isLoading = false
    }

    val filteredApps = remember(allApps, searchQuery) {
        if (searchQuery.isBlank()) allApps
        else allApps.filter {
            it.label.contains(searchQuery, ignoreCase = true) ||
                    it.packageName.contains(searchQuery, ignoreCase = true)
        }
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 260.dp, max = 540.dp)
                .padding(horizontal = 20.dp)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Select an App",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search apps...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            )

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(32.dp))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(filteredApps, key = { it.packageName }) { app ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(14.dp))
                                .clickable { onSelectApp(app) },
                            shape = RoundedCornerShape(14.dp),
                            color = MaterialTheme.colorScheme.surfaceContainerHigh
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp, horizontal = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                if (app.iconBitmap != null) {
                                    Image(
                                        bitmap = app.iconBitmap.asImageBitmap(),
                                        contentDescription = app.label,
                                        modifier = Modifier.size(36.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Apps,
                                        contentDescription = app.label,
                                        modifier = Modifier.size(36.dp),
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = app.label,
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = app.packageName,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
