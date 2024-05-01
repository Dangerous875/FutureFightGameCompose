package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroGameScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetCombatDataScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


lateinit var superHero1 : SuperHeroItem
lateinit var superHero2 : SuperHeroItem

@HiltViewModel
class CombatViewModel @Inject constructor(getCombatDataScreen: GetCombatDataScreen) : ViewModel() {

    private var _superHeroPlayer = MutableStateFlow<SuperHeroItem?>(null)
    val superHeroPlayer = _superHeroPlayer.asStateFlow()
    private var _superHeroCom = MutableStateFlow<SuperHeroItem?>(null)
    val superHeroCom = _superHeroCom.asStateFlow()
    private var _buttonEnable = MutableStateFlow(true)
    val buttonEnable = _buttonEnable.asStateFlow()


    init {
        val combatDataScreen = getCombatDataScreen()
        superHero1 = combatDataScreen.playerCharacter!!
        superHero2 = combatDataScreen.comCharacter!!
        Log.i("asd",superHero1.toString())
        Log.i("asd",superHero2.toString())
        _superHeroPlayer.value = superHero1
        _superHeroCom.value = superHero2
    }


    fun initAttack(){
        _buttonEnable.value = false
        viewModelScope.launch {
            attackPlayer()
            delay(5000)
            attackCom()
            _buttonEnable.value = true

        }

    }

    private fun attackCom() {
        var lifeCom = superHero1.powerstats.durability.toInt()
        val strengthPlayer = superHero2.powerstats.strength.toInt()
        lifeCom -= (strengthPlayer - 80)
        superHero1.powerstats.durability = lifeCom.toString()
        _superHeroPlayer.value = superHero1
    }

    private fun attackPlayer() {
        var lifeCom = superHero2.powerstats.durability.toInt()
        val strengthPlayer = superHero1.powerstats.strength.toInt()
        lifeCom -= (strengthPlayer - 6)
        superHero2.powerstats.durability = lifeCom.toString()
        _superHeroCom.value = superHero2
    }


}