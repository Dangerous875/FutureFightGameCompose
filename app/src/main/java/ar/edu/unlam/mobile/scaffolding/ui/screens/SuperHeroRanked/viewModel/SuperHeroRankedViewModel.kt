package ar.edu.unlam.mobile.scaffolding.ui.screens.SuperHeroRanked.viewModel

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroWinRateFromDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuperHeroRankedViewModel @Inject constructor(private val getSuperHeroWinRate:GetSuperHeroWinRateFromDataBase):ViewModel(){
}