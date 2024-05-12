package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel


import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen.viewmodel.superHero1
import ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen.viewmodel.superHero2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ResultViewModel : ViewModel() {

    private val _navegarPantallaResultado = MutableStateFlow(false)
    val navegarPantallaResultado: StateFlow<Boolean> = _navegarPantallaResultado
    private val _resultado = MutableStateFlow("PERDEDOR")
    val resultado: StateFlow<String> = _resultado

    fun lifeCheck() {
        val lifeCom = superHero2.powerstats.durability.toInt()
        val lifePlay = superHero1.powerstats.durability.toInt()
        if (lifeCom <= 0) {
            _navegarPantallaResultado.value = true
            _resultado.value = "GANADOR"
        }else if(lifePlay <= 0){
            _navegarPantallaResultado.value = true
        }
    }

    }

