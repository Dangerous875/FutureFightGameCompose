package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.Background
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
lateinit var backgroundData : Background

@HiltViewModel
class CombatViewModel @Inject constructor(getCombatDataScreen: GetCombatDataScreen) : ViewModel() {

    private var _superHeroPlayer = MutableStateFlow<SuperHeroItem?>(null)
    val superHeroPlayer = _superHeroPlayer.asStateFlow()
    private var _superHeroCom = MutableStateFlow<SuperHeroItem?>(null)
    val superHeroCom = _superHeroCom.asStateFlow()
    private var _background = MutableStateFlow<Background?>(null)
    val background = _background.asStateFlow()
    private var _buttonEnable = MutableStateFlow(true)
    val buttonEnable = _buttonEnable.asStateFlow()
    private var _attackEffect = MutableStateFlow(false)
    val attackEffect = _attackEffect.asStateFlow()
    private var _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()


    init {
        val combatDataScreen = getCombatDataScreen()
        _isLoading.value = true
        viewModelScope.launch {
            delay(15000)
            superHero1 = combatDataScreen.playerCharacter!!
            superHero2 = combatDataScreen.comCharacter!!
            backgroundData = combatDataScreen.background!!
            _superHeroPlayer.value = superHero1
            _superHeroCom.value = superHero2
            _background.value = backgroundData
            _isLoading.value = false
        }

    }


    fun initAttack(){
        _attackEffect.value = true
        _buttonEnable.value = false
        viewModelScope.launch {
            attackPlayer()
            delay(5000)
            attackCom()
            _buttonEnable.value = true
            _attackEffect.value = false

        }

    }

    private fun attackCom() {
        var lifeCom = superHero1.powerstats.durability.toInt()
        val strengthPlayer = superHero2.powerstats.strength.toInt()
        lifeCom -= strengthPlayer
        superHero1.powerstats.durability = lifeCom.toString()
        _superHeroPlayer.value = superHero1
    }

    private fun attackPlayer() {
        var lifeCom = superHero2.powerstats.durability.toInt()
        val strengthPlayer = superHero1.powerstats.strength.toInt()
        lifeCom -= strengthPlayer
        superHero2.powerstats.durability = lifeCom.toString()
        _superHeroCom.value = superHero2
    }


}