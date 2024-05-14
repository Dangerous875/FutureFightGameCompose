package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroCombatResultScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        viewModelScope.launch {
            _result.value = getResultDataScreen()
            delay(5000)
            _isLoading.value = false
        }
    }

}