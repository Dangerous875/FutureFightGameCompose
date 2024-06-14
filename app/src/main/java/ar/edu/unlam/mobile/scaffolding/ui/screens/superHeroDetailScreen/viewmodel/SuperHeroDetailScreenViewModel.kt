package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroDetailScreen.viewmodel

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SuperHeroDetailScreenViewModel @Inject constructor(
    getSuperHeroDetail: GetSuperHeroDetail)
    : ViewModel() {

    private val _playerDetailScreen = MutableStateFlow<SuperHeroItem?>(null)
    val playerDetailScreen = _playerDetailScreen.asStateFlow()

    init {
        val superHero = getSuperHeroDetail()
        if (superHero != null) {
            _playerDetailScreen.value = superHero
        }
    }
}