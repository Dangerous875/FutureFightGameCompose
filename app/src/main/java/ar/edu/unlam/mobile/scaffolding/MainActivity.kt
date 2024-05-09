package ar.edu.unlam.mobile.scaffolding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.presentationScreen.ui.PresentationScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.ui.SelectCharacterScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.ui.viewModel.SelectCharacterViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.SuperHeroResult
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen.SuperHeroCombatScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroDetailScreen.SuperHeroDetailScreen
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
                    val selectCharacterViewModel: SelectCharacterViewModel = hiltViewModel()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.SelectCharacterScreen.route
                    ) {
                        composable(Routes.PresentationScreen.route) {
                            PresentationScreen(navController = navigationController)
                        }
                        composable(Routes.SelectCharacterScreen.route) {
                            SelectCharacterScreen(
                                navController = navigationController,
                                selectCharacterViewModel
                            )
                        }
                        composable(Routes.SuperHeroCombatScreen.route) {
                            SuperHeroCombatScreen()
                        }
                        composable(Routes.SuperHeroCombatResultScreen.route) {
                            SuperHeroResult(navController = navigationController)
                        }
                        composable(Routes.SuperHeroDetailScreen.route) {
                            SuperHeroDetailScreen(
                                navController = navigationController,
                                selectCharacterViewModel
                            )
                        }

                    }

                }
            }
        }
    }
}


