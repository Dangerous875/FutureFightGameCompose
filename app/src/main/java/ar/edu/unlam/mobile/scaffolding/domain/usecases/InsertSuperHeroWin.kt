package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import ar.edu.unlam.mobile.scaffolding.domain.Model.SuperHeroWinRate
import javax.inject.Inject

class InsertSuperHeroWin @Inject constructor(private val superHeroRepository: SuperHeroRepository)  {

    suspend operator fun invoke(superHeroWinRate: SuperHeroWinRate){
        superHeroRepository.insertSuperHeroWin(superHeroWinRate)
    }

}
