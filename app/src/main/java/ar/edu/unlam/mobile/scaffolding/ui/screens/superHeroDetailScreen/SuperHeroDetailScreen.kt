package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroDetailScreen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.ui.components.ButtonWithBackgroundImage
import ar.edu.unlam.mobile.scaffolding.ui.components.mediaPlayer
import ar.edu.unlam.mobile.scaffolding.ui.components.screenSize
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.ui.viewModel.SelectCharacterViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroDetailScreen.viewmodel.SuperHeroDetailScreenViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun SuperHeroDetailScreen(
    navController: NavHostController,
    selectCharacterViewModel: SelectCharacterViewModel,
    superHeroDetailScreenViewModel: SuperHeroDetailScreenViewModel = hiltViewModel(),
) {

    val hero by superHeroDetailScreenViewModel.playerDetailScreen.collectAsState()
    val context = LocalContext.current
    val audioPosition = selectCharacterViewModel.audioPosition.collectAsState()
    val audio = mediaPlayer(context, audioPosition)
    val screenSizeSmall: Boolean = screenSize(context)

    val topPadding: Int
    val fontSizeText: Int
    val paddingTop: Int
    val fontSizeName : Int

    if (screenSizeSmall) {
        topPadding = 200
        fontSizeText = 8
        paddingTop = 8
        fontSizeName = 34
    } else {
        topPadding = 300
        fontSizeText = 10
        paddingTop = 16
        fontSizeName = 42
    }



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = hero!!.image.url),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp), contentScale = ContentScale.Crop
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topPadding.dp, start = 1.dp, end = 1.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                shape = RoundedCornerShape(42.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = hero!!.name,
                        fontSize = fontSizeName.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = paddingTop.dp),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black

                    )

                    Text(
                        text = hero!!.biography.fullName,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = (paddingTop/2).dp),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black

                    )

                    Text(
                        text = hero!!.biography.publisher,
                        fontSize = 8.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = (paddingTop/2).dp),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = Color.White

                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(230.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1.0f)
                                .padding(horizontal = 4.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            hero!!.powerstats.intelligence
                                                .toInt()
                                                .plus(100).dp
                                        )
                                        .background(colorResource(id = R.color.superhero_stat_intelligence))
                                )
                                Text(
                                    text = stringResource(id = R.string.Intelligence),
                                    fontSize = fontSizeText.sp
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1.0f)
                                .padding(horizontal = 4.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            hero!!.powerstats.strength
                                                .toInt()
                                                .plus(100).dp
                                        )
                                        .background(colorResource(id = R.color.superhero_stat_strength))
                                )
                                Text(
                                    text = stringResource(id = R.string.Strength),
                                    fontSize = fontSizeText.sp
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1.0f)
                                .padding(horizontal = 4.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            hero!!.powerstats.speed
                                                .toInt()
                                                .plus(100).dp
                                        )
                                        .background(colorResource(id = R.color.superhero_stat_speed))
                                )
                                Text(text = stringResource(id = R.string.Speed), fontSize = fontSizeText.sp)
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1.0f)
                                .padding(horizontal = 4.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            hero!!.powerstats.durability
                                                .toInt()
                                                .plus(100).dp
                                        )
                                        .background(colorResource(id = R.color.superhero_stat_durability))
                                )
                                Text(
                                    text = stringResource(id = R.string.Durability),
                                    fontSize = fontSizeText.sp
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1.0f)
                                .padding(horizontal = 4.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            hero!!.powerstats.power
                                                .toInt()
                                                .plus(100).dp
                                        )
                                        .background(colorResource(id = R.color.superhero_stat_power))
                                )
                                Text(text = stringResource(id = R.string.Power), fontSize = fontSizeText.sp)
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1.0f)
                                .padding(horizontal = 4.dp)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(
                                            hero!!.powerstats.combat
                                                .toInt()
                                                .plus(100).dp
                                        )
                                        .background(colorResource(id = R.color.superhero_stat_combat))
                                )
                                Text(text = stringResource(id = R.string.Combat), fontSize = fontSizeText.sp)
                            }
                        }


                    }

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        ButtonWithBackgroundImage(
                            imageResId = R.drawable.iv_button,
                            onClick = {
                                navController.navigate(Routes.SelectCharacterScreen.route) {
                                    popUpTo(Routes.SelectCharacterScreen.route) { inclusive = true }

                                    selectCharacterViewModel.setAudioPosition(audio.currentPosition)

                                }
                            },
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .width(300.dp)
                                .height(80.dp)
                                .padding(bottom = 22.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.BACK),
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(Font(R.font.font_firestar)),
                                fontStyle = FontStyle.Italic,
                                fontSize = 28.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                BackHandler {
                    navController.navigate(Routes.SelectCharacterScreen.route) {
                        popUpTo(Routes.SelectCharacterScreen.route) { inclusive = true }
                    }
                    selectCharacterViewModel.setAudioPosition(audio.currentPosition)
                }
            }
        }

    }
}





