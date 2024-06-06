package ar.edu.unlam.mobile.scaffolding.ui.screens.SuperHeroRanked

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.SuperHeroRanked.viewModel.SuperHeroRankedViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel.CombatResultViewModel

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewTest(){
    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.Black) ) {

    }
}
@Composable
fun SuperHeroRankedScreen(
    navController: NavHostController,
    viewModel:SuperHeroRankedViewModel = hiltViewModel()
) {
ViewTest()

}