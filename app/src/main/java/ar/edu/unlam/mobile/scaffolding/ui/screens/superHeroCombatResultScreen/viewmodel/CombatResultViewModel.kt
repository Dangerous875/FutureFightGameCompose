package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.ResultDataScreen

import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetResultDataScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CombatResultViewModel @Inject constructor(private val getResultDataScreen: GetResultDataScreen) :
    ViewModel() {

    private val _result = MutableStateFlow<ResultDataScreen?>(null)
    val result = _result.asStateFlow()
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private val _playerWin = MutableStateFlow(false)
    val playerWin = _playerWin.asStateFlow()
    private val _resultImageRes = MutableStateFlow(R.drawable.im_loser)
    val resultImageRes = _resultImageRes.asStateFlow()
    private val winnerImageRes = R.drawable.im_trofeo
    private val loserImageRes = R.drawable.im_loser


    init {
        viewModelScope.launch {
            _result.value = getResultDataScreen()
            _playerWin.value = checkIfPlayerWin(getResultDataScreen())
            _resultImageRes.value = if (_playerWin.value) {
                R.drawable.im_ganador
            } else {
                R.drawable.im_loser
            }

            delay(5000)
            _isLoading.value = false
        }
    }

    private fun checkIfPlayerWin(resultDataScreen: ResultDataScreen):Boolean {
        val playerLife = resultDataScreen.resultDataScreen!!.superHeroPlayer.life
        val comLife = resultDataScreen.resultDataScreen!!.superHeroCom.life
        return playerLife>comLife
    }
    fun getPlayerResultImageRes(): Int {
        return if (playerWin.value) {
            winnerImageRes
        } else {
            loserImageRes
        }
    }

    fun getComResultImageRes(): Int {
        return if (playerWin.value) {
            loserImageRes
        } else {
            winnerImageRes
        }
    }
    fun resetLife(){
        val resultData = _result.value ?: return

        resultData.resultDataScreen!!.superHeroPlayer.life =resultData.resultDataScreen!!.lifePlay
        resultData.resultDataScreen!!.superHeroCom.life = resultData.resultDataScreen!!.lifeCom }

}