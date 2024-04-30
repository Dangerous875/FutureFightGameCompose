package ar.edu.unlam.mobile.scaffolding.ui.components

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import ar.edu.unlam.mobile.scaffolding.MainActivity

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun SetOrientationScreen(context: Context, orientation: Boolean, hideStatusBar: Boolean = false) {
    val activity = context as? Activity
    val window = (context as? MainActivity)?.window

    if (activity != null) {
        if (orientation) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

    }

    if (hideStatusBar) {
        WindowInsetsControllerCompat(window!!, window.decorView).run {
            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}