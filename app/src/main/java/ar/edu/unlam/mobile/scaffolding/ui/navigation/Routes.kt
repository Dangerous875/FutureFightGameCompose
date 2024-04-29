package ar.edu.unlam.mobile.scaffolding.ui.navigation

sealed class Routes(val route : String){
    data object PresentationScreen : Routes("PresentationScreen")
    data object SelectCharacterScreen : Routes("SelectCharacterScreen")
    data object SuperHeroGameScreen : Routes("SuperHeroGameScreen")
    data object ResultScreen : Routes("ResultScreen")

}
