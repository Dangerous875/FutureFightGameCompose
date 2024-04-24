package ar.edu.unlam.mobile.scaffolding.ui.screens.presentationScreen.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.ui.screens.presentationScreen.data.model.WallpaperLogos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresentationScreenViewModel @Inject constructor(private val wallpaperLogos: WallpaperLogos):ViewModel() {

    private val _logos = MutableStateFlow(wallpaperLogos.logos[0])
    val logos = _logos.asStateFlow()
    private var initRandomLogo = true

    init {
            viewModelScope.launch {
                while (initRandomLogo) {
                    delay(5000)
                    val list = wallpaperLogos.logos
                    val randomNumber = (list.indices).random()
                    _logos.value = list[randomNumber]
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

