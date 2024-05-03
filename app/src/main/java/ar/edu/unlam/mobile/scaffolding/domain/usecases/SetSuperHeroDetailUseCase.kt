package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject

class SetSuperHeroDetailUseCase @Inject constructor(private val repository: SuperHeroRepository) {

    operator fun invoke(hero:SuperHeroItem){
        repository.setHeroDetail(hero)
    }
}