package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.Background
import ar.edu.unlam.mobile.scaffolding.data.local.SuperHeroCombat
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetCombatDataScreen
import ar.edu.unlam.mobile.scaffolding.domain.usecases.SetResultDataScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

lateinit var superHero1: SuperHeroCombat // SuperHeroCombat
lateinit var superHero2: SuperHeroCombat // SuperHeroCombat
lateinit var backgroundData: Background

@HiltViewModel
class CombatViewModel @Inject constructor(
   getCombatDataScreen: GetCombatDataScreen,
   private val setResultDataScreen: SetResultDataScreen
) : ViewModel() {

    private var _superHeroPlayer = MutableStateFlow<SuperHeroCombat?>(null)
    val superHeroPlayer = _superHeroPlayer.asStateFlow()
    private var _superHeroCom = MutableStateFlow<SuperHeroCombat?>(null)
    val superHeroCom = _superHeroCom.asStateFlow()
    private var _background = MutableStateFlow<Background?>(null)
    val background = _background.asStateFlow()
    private var _buttonEnable = MutableStateFlow(true)
    val buttonEnable = _buttonEnable.asStateFlow()
    private var _attackEffect = MutableStateFlow(false)
    val attackEffect = _attackEffect.asStateFlow()
    private var _audioAttack = MutableStateFlow(R.raw.raw_attack1)
    val audioAttack = _audioAttack.asStateFlow()
    private var _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private var _roundCount = MutableStateFlow(1)
    val roundCount = _roundCount.asStateFlow()
    var lifePlayer = ""
    var lifeCom = ""

    init {
        val combatDataScreen = getCombatDataScreen()
        _isLoading.value = true
        viewModelScope.launch {
            delay(9000)
            superHero1 = combatDataScreen.playerCharacter!!
            superHero2 = combatDataScreen.comCharacter!!
            backgroundData = combatDataScreen.background!!
            _superHeroPlayer.value = superHero1
            _superHeroCom.value = superHero2
            _background.value = backgroundData
            lifePlayer = superHero1.life.toString()
            lifeCom = superHero2.life.toString()
            _isLoading.value = false
        }
    }

    fun initAttack() {
        _attackEffect.value = true
        _buttonEnable.value = false
        viewModelScope.launch {
            attackPlayer()
            delay(1500)
            attackCom()
            _buttonEnable.value = true
            _attackEffect.value = false
            _roundCount.value += 1

        }
    }

    private fun attackCom() {
        var lifePlayer = superHero1.life
        val damageAbsPlayer = superHero1.damageAbs
        val attackCom = superHero2.attack.minus(superHero2.damagePenance)
        lifePlayer = lifePlayer.minus(attackCom.minus(damageAbsPlayer))
        superHero1.life = lifePlayer
        _superHeroPlayer.value = superHero1
    }

    private fun attackPlayer() {
        var lifeCom = superHero2.life
        val damageAbsCom = superHero2.damageAbs
        val attackPlayer = superHero1.attack.minus(superHero1.damagePenance)
        lifeCom = lifeCom.minus(attackPlayer.minus(damageAbsCom))
        superHero2.life = lifeCom
        _superHeroCom.value = superHero2
    }

    fun setDataScreenResult(superHeroPlayer: SuperHeroCombat, superHeroCombat: SuperHeroCombat) {
        setResultDataScreen(superHeroPlayer,superHeroCombat)
    }

    fun getRandomAudioAttack(){
        val audio = listOf(R.raw.raw_attack1,R.raw.raw_attack2,R.raw.raw_attack3,R.raw.raw_attack4,R.raw.raw_attack5)
        val randomAudio = (audio.indices).random()
        _audioAttack.value =  audio[randomAudio]
    }
}

