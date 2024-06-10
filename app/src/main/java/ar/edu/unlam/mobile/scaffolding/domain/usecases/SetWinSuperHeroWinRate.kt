package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject

class SetWinSuperHeroWinRate
    @Inject
    constructor(private val superHeroRepository: SuperHeroRepository)  {

        suspend operator fun invoke(nameSuperHero:String,win:Int){
            superHeroRepository.setWinSuperHeroWinRate(nameSuperHero,win)
        }

    }
