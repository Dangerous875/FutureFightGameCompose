package ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.di.NetworkUtils
import ar.edu.unlam.mobile.scaffolding.data.local.Background
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetCombatBackgroundDataUseCase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroListByName
import ar.edu.unlam.mobile.scaffolding.domain.usecases.SetCombatDataScreen
import ar.edu.unlam.mobile.scaffolding.domain.usecases.SetSuperHeroDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
    private val setSuperHeroDetailUseCase: SetSuperHeroDetailUseCase,
    private val getCombatBackgroundDataUseCase: GetCombatBackgroundDataUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private val _superHeroListPlayer = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListPlayer = _superHeroListPlayer.asStateFlow()
    private val _superHeroListCom = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroListCom = _superHeroListCom.asStateFlow()
    private val _playerSelected = MutableStateFlow<SuperHeroItem?>(null)
    val playerSelected = _playerSelected.asStateFlow()
    private val _comSelected = MutableStateFlow<SuperHeroItem?>(null)
    val comSelected = _comSelected.asStateFlow()
    private val _background = MutableStateFlow<Background?>(null)
    val background = _background.asStateFlow()
    private val _audioPosition = MutableStateFlow(0)
    val audioPosition = _audioPosition.asStateFlow()
    private val _backgroundData = MutableStateFlow<List<Background>>(emptyList())
    val backgroundData = _backgroundData.asStateFlow()
    private val _isInternetAvailable = MutableStateFlow(networkUtils.isInternetAvailable())
    val isInternetAvailable = _isInternetAvailable.asStateFlow()

    init {
        viewModelScope.launch {
            delay(5000)
            initListHero()
            _isLoading.value = false
        }
    }

    fun initListHero() {
        _isInternetAvailable.value = networkUtils.isInternetAvailable()
        viewModelScope.launch {
            if (!isInternetAvailable.value) {
                _isLoading.value = true
                delay(5000)
            }
            playerListDefault = getSuperHeroListByName(getRandomList())
            comListDefault = getSuperHeroListByName(getRandomList())
            _superHeroListPlayer.value = playerListDefault
            _superHeroListCom.value = comListDefault
            _backgroundData.value = getCombatBackgroundDataUseCase()
            if (!isInternetAvailable.value) {
                _isLoading.value = false
            }
        }
    }

    private fun getRandomList(): String {
        val randomList =
            listOf("war", "iro","sup","dar","de","su", "ca", "ba", "sp", "go", "f", "hu", "ap", "man", "th", "ir", "dr", "do")
        return randomList.random()

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

    fun setCombatData(player: SuperHeroItem, com: SuperHeroItem, background: Background) {
        setCombatDataScreen(player, com, background)
    }

    fun setPlayer(player: SuperHeroItem) {
        if (player == _playerSelected.value) {
            _playerSelected.value = null
        } else {
            _playerSelected.value = player
        }

    }

    fun setCom(com: SuperHeroItem) {
        if (com == _comSelected.value) {
            _comSelected.value = null
        } else {
            _comSelected.value = com
        }

    }

    fun setBackground(background: Background) {
        if (background == _background.value) {
            _background.value = null
        } else {
            _background.value = background
        }

    }


    fun setSuperHeroDetail(hero: SuperHeroItem) {
        setSuperHeroDetailUseCase(hero)
    }

    fun setAudioPosition(position: Int) {
        _audioPosition.value = position + 1
    }
}