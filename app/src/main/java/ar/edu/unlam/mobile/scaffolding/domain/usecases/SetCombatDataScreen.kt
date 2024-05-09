package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.Background
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject

class SetCombatDataScreen @Inject constructor(private val superHeroRepository: SuperHeroRepository) {

    operator fun invoke(player : SuperHeroItem, com : SuperHeroItem , background: Background){
        superHeroRepository.setCombatDataScreen(player,com,background)
    }
}