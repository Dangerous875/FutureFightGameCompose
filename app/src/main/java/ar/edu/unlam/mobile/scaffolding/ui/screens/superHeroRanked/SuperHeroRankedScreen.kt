package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroRanked


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.OrientationScreen

import ar.edu.unlam.mobile.scaffolding.domain.model.SuperHeroWinRate
import ar.edu.unlam.mobile.scaffolding.ui.components.SetOrientationScreen

import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroRanked.viewModel.SuperHeroRankedViewModel
import coil.compose.rememberAsyncImagePainter


@Composable

fun ViewTest(superHeroWins: List<SuperHeroWinRate>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        items(superHeroWins) {

            Row {
                Image(
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)

                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(80.dp))
                    Text(
                        text = "VICTORIES ",
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.font_firestar)),
                        fontStyle = FontStyle.Italic,
                        fontSize = 28.sp,
                        color = Color.Black,

                        )
                    Text(text = it.winRate.toString(), fontSize = 30.sp)
                }
            }
            Text(text = it.name, fontFamily = FontFamily.Serif, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}

@Composable
fun SuperHeroRankedScreen(
    navController: NavHostController,
    viewModel: SuperHeroRankedViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    SetOrientationScreen(
        context = context,
        orientation = OrientationScreen.PORTRAIT.orientation
    )


    val superHeroWins by viewModel.superHerosWin.collectAsState()
    ViewTest(superHeroWins)

}