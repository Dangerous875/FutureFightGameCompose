package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroGameScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.OrientationScreen
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.ui.components.ButtonWithBackgroundImage
import ar.edu.unlam.mobile.scaffolding.ui.components.SetOrientationScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroGameScreen.viewmodel.CombatViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun SuperHeroCombatScreen(viewModel: CombatViewModel = hiltViewModel()) {
    val superHeroPlayer by viewModel.superHeroPlayer.collectAsState()
    val superHeroCom by viewModel.superHeroCom.collectAsState()
    val enableButton by viewModel.buttonEnable.collectAsState()

    if (superHeroPlayer == null && superHeroCom == null) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {

        SetOrientationScreen(
            context = LocalContext.current,
            orientation = OrientationScreen.LANDSCAPE.orientation,
            hideStatusBar = true
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.iv_combatscreen1),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .align(Alignment.BottomStart)
                    .padding(start = 32.dp, bottom = 42.dp),
                shape = CardDefaults.elevatedShape,
                elevation = CardDefaults.cardElevation(40.dp)
            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(superHeroPlayer?.image?.url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = superHeroPlayer!!.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        color = Color.White
                    )
                }
            }

            Card(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .align(Alignment.BottomEnd)
                    .padding(end = 32.dp, bottom = 42.dp),
                shape = CardDefaults.elevatedShape,
                elevation = CardDefaults.cardElevation(40.dp)
            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(superHeroCom?.image?.url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = superHeroCom!!.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        color = Color.White
                    )
                }
            }

            Text(text = "VS", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            )
            {
                ButtonWithBackgroundImage(
                    imageResId = R.drawable.iv_attack,
                    onClick = { viewModel.initAttack() },
                    enabledButton = enableButton,
                    modifier = Modifier
                        .height(150.dp)
                        .width(350.dp)
                ) {
                    Text(
                        text = "Attack",
                        fontSize = 36.sp,
                        modifier = Modifier.padding(horizontal = 36.dp),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(30.dp)
                    .border(1.dp, Color.Black)
                    .background(Color.White)
            ) {

                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(superHeroPlayer!!.powerstats.durability.toInt().dp)
                        .background(setColorLifePlayer(superHeroPlayer!!))
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Player",
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.Gray
                    )
                    Text(
                        text = "${superHeroPlayer!!.powerstats.durability}/300",
                        textAlign = TextAlign.End,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(end = 8.dp, start = 120.dp),
                        color = Color.Gray
                    )
                }

            }
            Text(text = "Round 1")
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(30.dp)
                    .border(1.dp, Color.Black)
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(superHeroCom!!.powerstats.durability.toInt().dp)
                        .background(setColorLifePlayer(superHeroCom!!))
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Com",
                        textAlign = TextAlign.Start,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.Gray
                    )
                    Text(
                        text = "${superHeroCom!!.powerstats.durability}/300",
                        textAlign = TextAlign.End,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(end = 8.dp, start = 130.dp),
                        color = Color.Gray
                    )
                }


            }
        }
    }
}

fun setColorLifePlayer(heroItem: SuperHeroItem): Color {
    val durability = heroItem.powerstats.durability.toInt()
    val lifeColor = when (durability) {
        in 0..100 -> Color.Red
        in 101..200 -> Color.Yellow
        else -> Color.Green
    }
    return lifeColor
}