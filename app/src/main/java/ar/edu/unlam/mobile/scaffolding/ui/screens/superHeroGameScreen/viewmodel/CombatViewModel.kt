package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroGameScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroAppearance
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroBiography
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroImage
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroPowerStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


lateinit var superHero1 : SuperHeroItem
lateinit var superHero2 : SuperHeroItem

@HiltViewModel
class CombatViewModel @Inject constructor() : ViewModel() {

    private var _superHeroPlayer = MutableStateFlow<SuperHeroItem?>(null)
    val superHeroPlayer = _superHeroPlayer.asStateFlow()
    private var _superHeroCom = MutableStateFlow<SuperHeroItem?>(null)
    val superHeroCom = _superHeroCom.asStateFlow()
    private var _buttonEnable = MutableStateFlow(true)
    val buttonEnable = _buttonEnable.asStateFlow()


    init {
        superHero1 = SuperHeroItem(
            id = "70", name = "Batman",
            powerStats = SuperHeroPowerStats(
                intelligence = "100",
                strength = "26",
                speed = "27",
                durability = "300",
                power = "47",
                combat = "100"
            ),
            biography = SuperHeroBiography(
                fullName = "Bruce Wayne",
                firstAppearance = "Detective Comics #27",
                publisher = "DC Comics",
                alignment = "good"
            ),
            appearance = SuperHeroAppearance(gender = "Male", race = "Human"),
            image = SuperHeroImage(url = "https://www.superherodb.com/pictures2/portraits/10/100/639.jpg")
        )

        superHero2 = SuperHeroItem(
            id = "644",
            name = "Superman",
            powerStats = SuperHeroPowerStats(
                intelligence = "94",
                strength = "100",
                speed = "100",
                durability = "300",
                power = "100",
                combat = "85"
            ),
            biography = SuperHeroBiography(
                fullName = "Clark Kent",
                firstAppearance = "ACTION COMICS #1",
                publisher = "Superman Prime One-Million",
                alignment = "good"
            ),
            appearance = SuperHeroAppearance(gender = "Male", race = "Kryptonian"),
            image = SuperHeroImage(url = "https://www.superherodb.com/pictures2/portraits/10/100/791.jpg")
        )
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
        var lifeCom = superHero1.powerStats.durability.toInt()
        val strengthPlayer = superHero2.powerStats.strength.toInt()
        lifeCom -= (strengthPlayer - 80)
        superHero1.powerStats.durability = lifeCom.toString()
        _superHeroPlayer.value = superHero1
    }

    private fun attackPlayer() {
        var lifeCom = superHero2.powerStats.durability.toInt()
        val strengthPlayer = superHero1.powerStats.strength.toInt()
        lifeCom -= (strengthPlayer - 6)
        superHero2.powerStats.durability = lifeCom.toString()
        _superHeroCom.value = superHero2
    }


}