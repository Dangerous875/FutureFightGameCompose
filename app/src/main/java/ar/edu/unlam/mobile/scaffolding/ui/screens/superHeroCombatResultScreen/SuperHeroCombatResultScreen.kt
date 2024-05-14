package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.data.local.OrientationScreen
import ar.edu.unlam.mobile.scaffolding.ui.components.SetOrientationScreen
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel.CombatResultViewModel


@Composable
fun SuperHeroCombatResultScreen(
    navController: NavHostController,
    viewModel: CombatResultViewModel = hiltViewModel()
) {
    val result by viewModel.result.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val context = LocalContext.current

    SetOrientationScreen(
        context = context,
        orientation = OrientationScreen.PORTRAIT.orientation
    )

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column {
                Text(text = result!!.resultDataScreen!!.superHeroPlayer.name)
                Text(text = result!!.resultDataScreen!!.superHeroCom.name)
            }

        }
    }

    BackHandler {
        navController.navigate(Routes.SelectCharacterScreen.route) {
            popUpTo(Routes.SelectCharacterScreen.route) {
                inclusive = true
            }
        }
    }


}