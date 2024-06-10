package ar.edu.unlam.mobile.scaffolding.ui.components

import android.content.Context
import androidx.compose.runtime.Composable
import kotlin.math.sqrt

@Composable
fun screenSize(context: Context): Boolean {

    val displayMetrics = context.resources.displayMetrics

    val screenWidthInPixels = displayMetrics.widthPixels
    val screenHeightInPixels = displayMetrics.heightPixels
    val densityDpi = displayMetrics.densityDpi

    val widthInInches = screenWidthInPixels / densityDpi.toDouble()
    val heightInInches = screenHeightInPixels / densityDpi.toDouble()
    val screenSizeInInches = sqrt(widthInInches * widthInInches + heightInInches * heightInInches)

    return if (screenSizeInInches < 5.81) {
        true
    } else {
        false
    }
}