package ar.edu.unlam.mobile.scaffolding.ui.screens.presentationScreen.ui

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.OrientationScreen.PORTRAIT
import ar.edu.unlam.mobile.scaffolding.ui.components.ButtonWithBackgroundImage
import ar.edu.unlam.mobile.scaffolding.ui.components.SetOrientationScreen
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.presentationScreen.ui.viewmodel.PresentationScreenViewModel


@Composable
fun PresentationScreen(
    navController: NavHostController,
    presentationScreenViewModel: PresentationScreenViewModel = hiltViewModel()
) {
    SetOrientationScreen(context = LocalContext.current, orientation = PORTRAIT.orientation)

    val logos by presentationScreenViewModel.logos.collectAsState()
    val context = LocalContext.current
    val audio =
        remember {
            MediaPlayer.create(context, R.raw.media_marvel)
                .apply { setVolume(0.6f, 0.6f) }
        }

    DisposableEffect(Unit) {
        audio.start()
        onDispose {
            audio.stop()
            audio.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {

        Image(
            painter = painterResource(id = logos.logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Image(
            painter = painterResource(id = R.drawable.iv_logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopStart)

        )

        ButtonWithBackgroundImage(
            imageResId = R.drawable.iv_button,
            onClick = {
                navController.navigate(Routes.SelectCharacterScreen.route)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(300.dp)
                .height(80.dp)
                .padding(bottom = 22.dp)
        ) {
            Text(
                text = stringResource(id = R.string.EnterGame),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.font_firestar)),
                fontStyle = FontStyle.Italic,
                fontSize = 28.sp,
                color = Color.Black
            )
        }

    }
}
