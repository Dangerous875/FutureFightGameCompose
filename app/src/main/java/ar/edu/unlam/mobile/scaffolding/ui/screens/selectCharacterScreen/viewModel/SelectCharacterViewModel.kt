package ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroListByName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private lateinit var playerListDefault : List<SuperHeroItem>
private lateinit var comListDefault : List<SuperHeroItem>
@HiltViewModel
class SelectCharacterViewModel @Inject constructor(private val getSuperHeroListByName: GetSuperHeroListByName) : ViewModel() {

    private val _superHeroListPlayer = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListPlayer = _superHeroListPlayer.asStateFlow()
    private val _superHeroListCom = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListCom = _superHeroListCom.asStateFlow()

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

    fun searchHeroByNameToPlayer(query :String){
        viewModelScope.launch {
            val list = getSuperHeroListByName(query)
            if (list.isNotEmpty()){
                _superHeroListPlayer.value = list
            }
        }
    }

    fun searchHeroByNameToCom(query :String){
        viewModelScope.launch {
            val list = getSuperHeroListByName(query)
            if (list.isNotEmpty()){
                _superHeroListCom.value = list
            }
        }
    }
}