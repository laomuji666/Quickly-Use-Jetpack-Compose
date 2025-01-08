package com.laomuji666.compose.feature.video

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoScreen(
    videoUri: String,
    viewModel: VideoScreenViewModel = hiltViewModel(),
    isFullScreen: Boolean = true
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    DisposableEffect(Unit) {
        viewModel.initializePlayer(context, videoUri)
        if (isFullScreen) {
            context.setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        } else {
            context.setScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
        onDispose {
            viewModel.savePlayerState()
            viewModel.releasePlayer()
        }
    }
    Column {
        Media3AndroidView(uiState)
    }
}

fun Context.setScreenOrientation(orientation: Int) {
    val activity = this.findActivity() ?: return
    activity.requestedOrientation = orientation
    if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
        hideSystemUi()
    } else {
        showSystemUi()
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity       -> this
    is ContextWrapper -> baseContext.findActivity()
    else              -> null
}

fun Context.hideSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, window.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun Context.showSystemUi() {
    val activity = this.findActivity() ?: return
    val window = activity.window ?: return
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(
        window,
        window.decorView
    ).show(WindowInsetsCompat.Type.systemBars())
}

@Composable
private fun Media3AndroidView(player: ExoPlayer?) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            PlayerView(context).apply {
                this.player = player
            }
        },
        update = { playerView ->
            playerView.player = player
        }
    )
}
