package ar.edu.unlam.mobile.scaffolding.ui.components

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun mediaPlayer(
    context: Context,
    audioPosition: State<Int>
): MediaPlayer {
    val audio = remember {
        MediaPlayer.create(context, R.raw.raw_selectcharacter)
            .apply { setVolume(0.1f, 0.1f) }
    }
    Log.i("audioPosition1", "${audioPosition.value}")

    DisposableEffect(Unit) {
        audio.let {
            it.seekTo(audioPosition.value)
            it.start()
        }
        onDispose {
            audio.stop()
            audio.release()
        }
    }
    return audio
}