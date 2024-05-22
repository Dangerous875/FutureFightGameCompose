package ar.edu.unlam.mobile.scaffolding.ui.components

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun PlayAudioEffect(
    randomTheme: Int,
    context: Context,
    attackEffect: Boolean
) {
    DisposableEffect(randomTheme) {
        val audio = MediaPlayer.create(context, randomTheme).apply {
            setVolume(1.0f, 1.0f)
        }

        if (attackEffect) {
            audio.start()
        }

        onDispose {
            audio.release()
        }
    }
}