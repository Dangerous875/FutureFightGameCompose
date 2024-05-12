package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.core.toSuperHeroCombat
import ar.edu.unlam.mobile.scaffolding.data.local.CombatDataScreen
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.random.Random

class GetCombatDataScreen @Inject constructor(private val superHeroRepository: SuperHeroRepository) {
    operator fun invoke():CombatDataScreen{
        return superHeroRepository.getCombatDataScreen()
    }
}
