package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroDetailScreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SuperHeroDetailScreenViewModel @Inject constructor():ViewModel() {
    private val _playerDetailScreen = MutableStateFlow<SuperHeroItem?>(null)
    val playerDetailScreen = _playerDetailScreen.asStateFlow()

    fun setPlayerDetailScreen(hero : SuperHeroItem){
        Log.i("hero",hero.toString())
        _playerDetailScreen.value = hero
        Log.i("hero",_playerDetailScreen.toString())
    }

}