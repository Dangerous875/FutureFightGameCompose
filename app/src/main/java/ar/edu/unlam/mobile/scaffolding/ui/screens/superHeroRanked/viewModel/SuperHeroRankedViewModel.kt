package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroRanked.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.domain.model.SuperHeroWinRate
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroWinRateFromDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroRankedViewModel @Inject constructor(private val getSuperHeroWinRate:GetSuperHeroWinRateFromDataBase):ViewModel(){
    private val _superHerosWin= MutableStateFlow<List<SuperHeroWinRate>>(emptyList())
    val superHerosWin=_superHerosWin.asStateFlow()

    init {
        viewModelScope.launch {

            _superHerosWin.value=getSuperHeroWinRate()
        }
    }
}