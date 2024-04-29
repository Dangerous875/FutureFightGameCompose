package ar.edu.unlam.mobile.scaffolding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.presentationScreen.ui.PresentationScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.result.ResultGame
import ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.SelectCharacterScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroGameScreen.SuperHeroGameScreen
import ar.edu.unlam.mobile.scaffolding.ui.theme.ScaffoldingV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldingV2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.PresentationScreen.route
                    ) {
                        composable(Routes.PresentationScreen.route) {
                            PresentationScreen(
                                navController = navigationController
                            )
                        }
                        composable(Routes.SelectCharacterScreen.route) {
                            SelectCharacterScreen(
                            )
                        }
                        composable(Routes.SuperHeroGameScreen.route){
                            SuperHeroGameScreen(navController = navigationController)
                        }
                        composable(Routes.ResultScreen.route){
                     //       ResultGame(navController = navigationController)
                        }

                    }

                }
            }
        }
    }
}


