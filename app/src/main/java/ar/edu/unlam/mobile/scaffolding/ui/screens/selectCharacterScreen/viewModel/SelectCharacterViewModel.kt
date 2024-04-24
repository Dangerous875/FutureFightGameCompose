package ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.domain.usecases.GetSuperHeroListByName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCharacterViewModel @Inject constructor(private val getSuperHeroListByName: GetSuperHeroListByName) : ViewModel() {

    private val _superHeroList = MutableStateFlow<List<SuperHeroItem>>(emptyList())
    val superHeroList = _superHeroList.asStateFlow()

//    fun searchHeroByNamePlayer(query :String){
//        viewModelScope.launch {
//            Log.i("asd",query)
//            val list = getSuperHeroListByName(query)
//            Log.i("asd",list.toString())
//            if (list.isNotEmpty()){
//                _superHeroList.value = list
//            }
//        }
//    }

    fun searchHeroByName(query :String){
        viewModelScope.launch {
            Log.i("asd",query)
            val list = getSuperHeroListByName(query)
            Log.i("asd",list.toString())
            if (list.isNotEmpty()){
                _superHeroList.value = list
            }
        }
    }
}