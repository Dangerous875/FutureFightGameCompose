package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen

import android.media.MediaPlayer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.OrientationScreen
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.ui.components.AttackEffect
import ar.edu.unlam.mobile.scaffolding.ui.components.ButtonWithBackgroundImage
import ar.edu.unlam.mobile.scaffolding.ui.components.SetOrientationScreen
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel.ResultViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen.viewmodel.CombatViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun SuperHeroCombatScreen(navController: NavHostController, viewModel: CombatViewModel = hiltViewModel(),viewModdelresult:ResultViewModel= hiltViewModel()) {
    val superHeroPlayer by viewModel.superHeroPlayer.collectAsState()
    val superHeroCom by viewModel.superHeroCom.collectAsState()
    val backgroundData by viewModel.background.collectAsState()
    val enableButton by viewModel.buttonEnable.collectAsState()
    val attackEffect by viewModel.attackEffect.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val navegarPantallaResultado by viewModdelresult.navegarPantallaResultado.collectAsState()

    val context = LocalContext.current

    SetOrientationScreen(
        context = LocalContext.current,
        orientation = OrientationScreen.LANDSCAPE.orientation,
        hideStatusBar = true
    )

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.iv_vs),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            LinearProgressIndicator(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp))
            Text(text = "Loading ...", modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp))
        }
    } else {
        if (navegarPantallaResultado) {
            LaunchedEffect(Unit) {
                navController.navigate(Routes.SuperHeroCombatResultScreen.route)
            }
        }

        val audio = remember {
            MediaPlayer.create(context, backgroundData!!.theme)
                .apply { setVolume(0.5f, 0.5f) }
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
        ) {
            Image(
                painter = painterResource(id = backgroundData!!.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // ICONS PARA ESPECIALES OPCIONALES ************************************************

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(bottom = 150.dp, start = 30.dp)
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)

            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(bottom = 150.dp, start = 90.dp)
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)

            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(bottom = 150.dp, start = 150.dp)
            ) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)

            }

            // ICONS PARA ESPECIALES OPCIONALES ************************************************

            Card(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .align(Alignment.BottomStart)
                    .padding(start = 32.dp, bottom = 42.dp),
                shape = CardDefaults.elevatedShape,
                elevation = CardDefaults.cardElevation(40.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = rememberAsyncImagePainter(superHeroPlayer!!.image.url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                colorResource(id = R.color.superhero_item_name)
                            )
                    ) {
                        Text(
                            text = superHeroPlayer!!.name,
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.BottomCenter),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                    }
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
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = rememberAsyncImagePainter(superHeroCom!!.image.url),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                colorResource(id = R.color.superhero_item_name)
                            )
                    ) {
                        Text(
                            text = superHeroCom!!.name,
                            fontSize = 24.sp,
                            modifier = Modifier.align(Alignment.BottomCenter),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                    }
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
                    onClick = { viewModel.initAttack()
                              viewModdelresult.lifeCheck()
                              },
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

        AttackEffect(attackEffect, enableButton)

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