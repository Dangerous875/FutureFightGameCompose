package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject

class GetSuperHeroListByName @Inject constructor(private val superHeroRepository: SuperHeroRepository) {

    suspend operator fun invoke(query :String):List<SuperHeroItem>{
        return superHeroRepository.getSuperHeroListByName(query)
    }
}