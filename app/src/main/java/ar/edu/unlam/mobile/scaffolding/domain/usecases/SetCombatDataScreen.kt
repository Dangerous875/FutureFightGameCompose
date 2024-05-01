package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject

class SetCombatDataScreen @Inject constructor(private val superHeroRepository: SuperHeroRepository) {

    operator fun invoke(player : SuperHeroItem, com : SuperHeroItem){
        superHeroRepository.setCombatDataScreen(player,com)
    }
}