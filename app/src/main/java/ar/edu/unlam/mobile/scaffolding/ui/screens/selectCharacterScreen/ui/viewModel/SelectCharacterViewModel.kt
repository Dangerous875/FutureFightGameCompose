package ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroListByName
import ar.edu.unlam.mobile.scaffolding.domain.usecases.SetCombatDataScreen
import ar.edu.unlam.mobile.scaffolding.domain.usecases.SetSuperHeroDetailUseCase
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
    private val setCombatDataScreen: SetCombatDataScreen,
    private val setSuperHeroDetailUseCase: SetSuperHeroDetailUseCase
) : ViewModel() {

    private val _superHeroListPlayer = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListPlayer = _superHeroListPlayer.asStateFlow()
    private val _superHeroListCom = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListCom = _superHeroListCom.asStateFlow()
    private val _playerSelected = MutableStateFlow<SuperHeroItem?>(null)
    val playerSelected = _playerSelected.asStateFlow()
    private val _comSelected = MutableStateFlow<SuperHeroItem?>(null)
    val comSelected = _comSelected.asStateFlow()
    private val _audioPosition = MutableStateFlow(0)
    val audioPosition = _audioPosition.asStateFlow()

    init {
        initListHero()
    }

    fun initListHero() {
        viewModelScope.launch {
            playerListDefault = getSuperHeroListByName(getRandomLetter())
            comListDefault = getSuperHeroListByName(getRandomLetter())
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

    fun setSuperHeroDetail(hero:SuperHeroItem){
        setSuperHeroDetailUseCase(hero)
    }

    fun setAudioPosition(position : Int){
        _audioPosition.value = position
    }
}