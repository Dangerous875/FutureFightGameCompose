package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.components.ButtonWithBackgroundImage
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


@Composable
fun SuperHeroResult(navController: NavHostController){

    Column (
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        val painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data ="https:www.mundodeportivo.com/alfabeta/hero/2024/04/batman-bruce-wayne-superman-dc.jpg?width=1200&aspect_ratio=16:9")
                    .apply(block = fun ImageRequest.Builder.() {

                    }).build()
            )
        val text="GANADOR"
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            Image(
                painter =painter,
                contentDescription = "Descripción de la imagen"
            )}

        Box(modifier = Modifier.fillMaxWidth().weight(1f), contentAlignment = Alignment.Center){
            Text(style = TextStyle(color=Color.Red),fontSize=50.sp, text = text,)}
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            ButtonWithBackgroundImage(
                imageResId = R.drawable.iv_button,
                onClick = {
                    navController.navigate(Routes.PresentationScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(300.dp)
                    .height(80.dp)
                    .padding(bottom = 22.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.Home),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.font_firestar)),
                    fontStyle = FontStyle.Italic,
                    fontSize = 28.sp,
                    color = Color.Black
                )
            }
        }
    }



}