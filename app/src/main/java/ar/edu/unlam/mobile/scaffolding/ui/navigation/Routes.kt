package ar.edu.unlam.mobile.scaffolding.ui.navigation

sealed class Routes(val route : String){
    data object PresentationScreen : Routes("PresentationScreen")
    data object SelectCharacterScreen : Routes("SelectCharacterScreen")
    data object OtraPantalla : Routes("OtraPantallaNavegacion")

    data object NuevaPantalla : Routes("NuevaPantallaNavegacion") //pantalla prueba git

    data object NuevaPantalla2 : Routes("NuevaPantallaNavegacion") //pantalla prueba git
}
