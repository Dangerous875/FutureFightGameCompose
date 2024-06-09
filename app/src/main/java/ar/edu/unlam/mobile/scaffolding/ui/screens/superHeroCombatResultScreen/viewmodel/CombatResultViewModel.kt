package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.ResultDataScreen
import ar.edu.unlam.mobile.scaffolding.domain.Model.SuperHeroWinRate

import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetResultDataScreen
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroWinRateFromDataBase
import ar.edu.unlam.mobile.scaffolding.domain.usecases.InsertSuperHeroWin
import ar.edu.unlam.mobile.scaffolding.domain.usecases.SetWinSuperHeroWinRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CombatResultViewModel @Inject constructor(private val getResultDataScreen: GetResultDataScreen, private val insertSuperHeroWin: InsertSuperHeroWin, private val setSuperHeroWin: SetWinSuperHeroWinRate, private val getSuperHeroWinRateFromDataBase: GetSuperHeroWinRateFromDataBase) :
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
private val _superHerosWin=MutableStateFlow<List<SuperHeroWinRate>>(emptyList())
private val superHerosWin=_superHerosWin.asStateFlow()
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

 fun saveWin(){
    viewModelScope.launch {
        _superHerosWin.value=getSuperHeroWinRateFromDataBase()
        var nameWin=_result.value!!.resultDataScreen?.superHeroPlayer?.name
        var imageWin=_result.value!!.resultDataScreen?.superHeroPlayer?.image
        if(!_playerWin.value){
            nameWin=_result.value!!.resultDataScreen?.superHeroCom?.name
            imageWin=_result.value!!.resultDataScreen?.superHeroCom?.image}

        if(_superHerosWin.value.filter { it.name.equals(nameWin)}.isEmpty()){
            val superHeroWin= SuperHeroWinRate(nameWin!!,imageWin!!.url,1)
            insertSuperHeroWin(superHeroWin)
        }else{
            var wins=  _superHerosWin.value.filter { it.name == nameWin }.first().winRate
            wins+=1
            setSuperHeroWin(nameWin!!,wins)
        }


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