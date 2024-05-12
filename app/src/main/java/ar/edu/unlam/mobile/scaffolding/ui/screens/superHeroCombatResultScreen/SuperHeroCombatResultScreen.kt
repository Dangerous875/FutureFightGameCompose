package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel.ResultViewModel


@Composable
fun SuperHeroCombatResultScreen(navController: NavHostController,viewModel: ResultViewModel= hiltViewModel()){
    val result by viewModel.resultado.collectAsState()
    Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
    Text(result, style = TextStyle(color=Color.White))
}
}