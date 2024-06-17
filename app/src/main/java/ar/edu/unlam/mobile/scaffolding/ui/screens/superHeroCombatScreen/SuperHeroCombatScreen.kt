package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen

import android.media.MediaPlayer
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.OrientationScreen
import ar.edu.unlam.mobile.scaffolding.data.local.SuperHeroCombat
import ar.edu.unlam.mobile.scaffolding.ui.components.AttackEffect
import ar.edu.unlam.mobile.scaffolding.ui.components.ButtonWithBackgroundImage
import ar.edu.unlam.mobile.scaffolding.ui.components.ExitConfirmation
import ar.edu.unlam.mobile.scaffolding.ui.components.SetOrientationScreen
import ar.edu.unlam.mobile.scaffolding.ui.components.StatsBattle
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen.viewmodel.CombatViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun SuperHeroCombatScreen(
    navController: NavHostController,
    viewModel: CombatViewModel = hiltViewModel()
) {
    val superHeroPlayer by viewModel.superHeroPlayer.collectAsState()
    val superHeroCom by viewModel.superHeroCom.collectAsState()
    val backgroundData by viewModel.background.collectAsState()
    val enableButton by viewModel.buttonEnable.collectAsState()
    val attackEffect by viewModel.attackEffect.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val roundCount by viewModel.roundCount.collectAsState()
    val lifePlayer = viewModel.lifePlayer
    val lifeCom = viewModel.lifeCom

    val navigationOK by viewModel.navigationDone.collectAsState()
    var showExitConfirmation by rememberSaveable { mutableStateOf(false) }
    val attackPlayer by viewModel.attackPlayer.collectAsState()
    val context = LocalContext.current

    val iconButtonPotion by viewModel.iconButtonPotion.collectAsState()
    val iconButtonPowerUp by viewModel.iconButtonPowerUp.collectAsState()
    val iconButtonDefensive by viewModel.iconButtonDefensive.collectAsState()

    val iconButtonPotionCom by viewModel.iconButtonPotionCom.collectAsState()
    val iconButtonPowerUpCom by viewModel.iconButtonPowerUpCom.collectAsState()
    val iconButtonDefensiveCom by viewModel.iconButtonDefensiveCom.collectAsState()

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
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Loading ...",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    } else {

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

        if ((superHeroPlayer!!.life <= 0 || superHeroCom!!.life <= 0) && !navigationOK) {
            viewModel.setDataScreenResult(
                superHeroPlayer = superHeroPlayer!!,
                superHeroCombat = superHeroCom!!
            )
            viewModel.markNavigationDone()
            navController.navigate(Routes.SuperHeroCombatResultScreen.route)
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

            // ICONS PARA ESPECIALES PLAYER START ************************************************

            IconButton(
                onClick = {
                    viewModel.healingPotion(lifePlayer.toInt())
                },
                enabled = (iconButtonPotion && enableButton),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(bottom = 150.dp, start = 35.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_pocion),
                    contentDescription = "healing potion",
                    tint = if (iconButtonPotion) {
                        Color.Green
                    } else {
                        Color.DarkGray
                    },
                    modifier = Modifier.size(70.dp)
                )
            }

            IconButton(
                onClick = {
                    viewModel.specialAttack()
                },
                enabled = iconButtonPowerUp && enableButton,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(bottom = 150.dp, start = 120.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_special_energy),
                    contentDescription = null,
                    tint = if (iconButtonPowerUp) {
                        Color.Red
                    } else {
                        Color.DarkGray
                    },
                    modifier = Modifier.size(40.dp)
                )
            }

            IconButton(
                onClick = {
                    viewModel.specialDefense()
                },
                enabled = iconButtonDefensive && enableButton ,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(bottom = 150.dp, start = 200.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_shield_healing),
                    contentDescription = null,
                    tint = if (iconButtonDefensive) {
                        Color.Yellow
                    } else {
                        Color.DarkGray
                    },
                    modifier = Modifier.size(37.dp)
                )
            }

            // ICONS PARA ESPECIALES PLAYER END ***********************************************

            // ICONS PARA ESPECIALES COM START ************************************************
            IconButton(
                onClick = { },
                enabled = (iconButtonPotionCom && enableButton),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(bottom = 150.dp, end = 200.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_pocion),
                    contentDescription = "healing potion",
                    tint = if (iconButtonPotionCom) {
                        Color.Green
                    } else {
                        Color.DarkGray
                    },
                    modifier = Modifier.size(70.dp)
                )
            }

            IconButton(
                onClick = { },
                enabled = iconButtonPowerUpCom && enableButton,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(bottom = 150.dp, end = 120.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_special_energy),
                    contentDescription = null,
                    tint = if (iconButtonPowerUpCom) {
                        Color.Red
                    } else {
                        Color.DarkGray
                    },
                    modifier = Modifier.size(40.dp)
                )
            }

            IconButton(
                onClick = { },
                enabled = iconButtonDefensiveCom && enableButton ,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(bottom = 150.dp, end = 35.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_shield_healing),
                    contentDescription = null,
                    tint = if (iconButtonDefensiveCom) {
                        Color.Yellow
                    } else {
                        Color.DarkGray
                    },
                    modifier = Modifier.size(37.dp)
                )
            }

            // ICONS PARA ESPECIALES COM END ************************************************

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

            Text(
                text = "VS", fontSize = 54.sp, modifier = Modifier.align(Alignment.Center),
                fontFamily = FontFamily(Font(R.font.font_bodoni_italic)), color = Color.White
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            )
            {
                ButtonWithBackgroundImage(
                    imageResId = R.drawable.iv_attack,
                    onClick = {
                        viewModel.getRandomAudioAttack()
                        viewModel.initAttack()
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

            StatsBattle(superHeroPlayer, paddingStart = 32)
            StatsBattle(superHeroCom, paddingStart = 588, paddingEnd = 32)
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
                        .width(superHeroPlayer!!.life.dp)
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
                        text = "${superHeroPlayer!!.life}/$lifePlayer",
                        textAlign = TextAlign.End,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(end = 8.dp, start = 120.dp),
                        color = Color.Gray
                    )
                }

            }

            Box(
                modifier = Modifier
                    .height(70.dp)
                    .width(200.dp)
                    .background(
                        if (backgroundData!!.background == R.drawable.iv_dragonballfight) {
                            Color.DarkGray
                        } else {
                            Color.Unspecified
                        }
                    ),
                contentAlignment = Alignment.TopCenter
            ) {
                Card(
                    shape = CardDefaults.elevatedShape,
                    elevation = CardDefaults.cardElevation(40.dp),
                    modifier = Modifier.align(Alignment.TopCenter)
                ) {
                    Text(
                        text = " Round $roundCount ",
                        modifier = Modifier.padding(4.dp),
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.font_bodoni_italic)),
                        color = Color.White
                    )
                }

                Icon(
                    painter = painterResource(id = setIconPlayer(attackPlayer)),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(35.dp)
                )

                Icon(
                    painter = painterResource(id = setIconCom(attackPlayer)),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(35.dp)
                )
                Text(
                    text = stringResource(id = setMessageAttack(attackPlayer)),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }

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
                        .width(superHeroCom!!.life.dp)
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
                        text = "${superHeroCom!!.life}/$lifeCom",
                        textAlign = TextAlign.End,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(end = 8.dp, start = 130.dp),
                        color = Color.Gray
                    )
                }


            }
        }
        AttackEffect(attackEffect, enableButton, context, viewModel)
    }

    BackHandler {
        showExitConfirmation = true
    }

    ExitConfirmation(
        show = showExitConfirmation,
        onDismiss = { showExitConfirmation = false },
        onConfirm = {
            navController.navigate(Routes.SelectCharacterScreen.route) {
                popUpTo(Routes.SelectCharacterScreen.route) {
                    inclusive = true
                }
            }
        },
        title = stringResource(id = R.string.ExitConfirmation),
        message = stringResource(id = R.string.ExitCombat)
    )
}

fun setMessageAttack(attackPlayer: Boolean): Int {
    return if (attackPlayer) {
        R.string.AttackMessage
    } else {
        R.string.DefenseMessage
    }
}

fun setIconCom(attackPlayer: Boolean): Int {
    return if (!attackPlayer) {
        R.drawable.icon_attacksword
    } else {
        R.drawable.icon_defense
    }
}

fun setIconPlayer(attackPlayer: Boolean): Int {
    return if (attackPlayer) {
        R.drawable.icon_attacksword
    } else {
        R.drawable.icon_defense
    }
}


fun setColorLifePlayer(heroItem: SuperHeroCombat): Color {
    val durability = heroItem.life
    val lifeColor = when (durability) {
        in 0..100 -> Color.Red
        in 101..200 -> Color.Yellow
        else -> Color.Green
    }
    return lifeColor
}