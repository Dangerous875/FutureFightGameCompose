package ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroListByName
import ar.edu.unlam.mobile.scaffolding.domain.usecases.SetCombatDataScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private lateinit var playerListDefault: List<SuperHeroItem>
private lateinit var comListDefault: List<SuperHeroItem>

@HiltViewModel
class SelectCharacterViewModel @Inject constructor(
    private val getSuperHeroListByName: GetSuperHeroListByName,
    private val setCombatDataScreen: SetCombatDataScreen
) : ViewModel() {

    private val _superHeroListPlayer = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListPlayer = _superHeroListPlayer.asStateFlow()
    private val _superHeroListCom = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListCom = _superHeroListCom.asStateFlow()
    private val _playerSelected = MutableStateFlow<SuperHeroItem?>(null)
    val playerSelected = _playerSelected.asStateFlow()
    private val _comSelected = MutableStateFlow<SuperHeroItem?>(null)
    val comSelected = _comSelected.asStateFlow()

    init {
        initListHero()
    }

    fun initListHero() {
        viewModelScope.launch {
            playerListDefault = getSuperHeroListByName(getRandomLetter())
            comListDefault = getSuperHeroListByName(getRandomLetter())
            Log.i("lista1", playerListDefault.toString())
            Log.i("lista1", comListDefault.toString())
            _superHeroListPlayer.value = playerListDefault
            _superHeroListCom.value = comListDefault
        }
    }

    private fun getRandomLetter(): String {
        return ('a'..'z').random().toString()

    }

    fun searchHeroByNameToPlayer(query: String) {
        viewModelScope.launch {
            val list = getSuperHeroListByName(query)
            if (list.isNotEmpty()) {
                _superHeroListPlayer.value = list
            }
        }
    }

    fun searchHeroByNameToCom(query: String) {
        viewModelScope.launch {
            val list = getSuperHeroListByName(query)
            if (list.isNotEmpty()) {
                _superHeroListCom.value = list
            }
        }
    }

    fun setCombatData(player : SuperHeroItem , com : SuperHeroItem){
        Log.i("asd",player.toString())
        Log.i("asd",com.toString())
        setCombatDataScreen(player,com)
    }

    fun setPlayer(player : SuperHeroItem){
        if (player == _playerSelected.value){
            _playerSelected.value = null
        }else{
            _playerSelected.value = player
        }

    }

    fun setCom(com : SuperHeroItem){
        if (com == _comSelected.value){
            _comSelected.value = null
        }else{
            _comSelected.value = com
        }

    }
}