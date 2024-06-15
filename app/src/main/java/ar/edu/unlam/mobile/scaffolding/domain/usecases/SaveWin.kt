package ar.edu.unlam.mobile.scaffolding.domain.usecases


import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import ar.edu.unlam.mobile.scaffolding.domain.model.SuperHeroWinRate
import kotlinx.coroutines.flow.MutableStateFlow

import javax.inject.Inject

class SaveWin @Inject constructor(private val superHeroRepository: SuperHeroRepository,private val getResultDataScreen: GetResultDataScreen){
    suspend operator fun invoke()
    {
        val result=getResultDataScreen().resultDataScreen
        val superHerosWin = MutableStateFlow<List<SuperHeroWinRate>>(emptyList())
        superHerosWin.value = superHeroRepository.getSuperHeroWinRateFromDataBase()
        var nameWin = result!!.superHeroPlayer.name
        var imageWin = result.superHeroPlayer.image
        if (result.superHeroPlayer.life<=0) {
            nameWin = result.superHeroCom.name
            imageWin = result.superHeroCom.image
        }

        if (superHerosWin.value.none { it.name == nameWin }) {
            val superHeroWin = SuperHeroWinRate(nameWin, imageWin.url, 1)
            superHeroRepository.insertSuperHeroWin(superHeroWin)
        } else {
            var wins = superHerosWin.value.first { it.name == nameWin }.winRate
            wins += 1
            superHeroRepository.setWinSuperHeroWinRate(nameWin,wins)

        }
    }


}