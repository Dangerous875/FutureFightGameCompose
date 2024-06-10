package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import ar.edu.unlam.mobile.scaffolding.domain.model.SuperHeroWinRate
import javax.inject.Inject

class GetSuperHeroWinRateFromDataBase @Inject constructor(private val superHeroRepository: SuperHeroRepository)  {
    suspend operator fun invoke():List<SuperHeroWinRate>{
        return superHeroRepository.getSuperHeroWinRateFromDataBase()
    }
}
