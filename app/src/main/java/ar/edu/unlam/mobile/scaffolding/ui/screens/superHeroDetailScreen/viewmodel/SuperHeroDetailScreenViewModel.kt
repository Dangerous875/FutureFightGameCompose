package ar.edu.unlam.mobile.scaffolding.ui.screens.superHeroDetailScreen.viewmodel

import android.util.Log
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
    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    init {
        val superHero = getSuperHeroDetail()
        if (superHero != null) {
            _playerDetailScreen.value = superHero
            _loading.value = false
        }
        Log.i("heroDetail", " view model ${playerDetailScreen.value.toString()}")
        Log.i("heroDetail", " view model ${loading.value}")
    }

}