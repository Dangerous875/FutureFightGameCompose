package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.Background
import ar.edu.unlam.mobile.scaffolding.data.local.CombatBackgroundsData
import ar.edu.unlam.mobile.scaffolding.data.local.CombatDataScreen
import ar.edu.unlam.mobile.scaffolding.data.local.HeroDetail
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.network.service.SuperHeroService
import javax.inject.Inject

class SuperHeroRepository @Inject constructor(
    private val superHeroService: SuperHeroService,
    private val combatDataScreen: CombatDataScreen,
    private val heroDetail: HeroDetail,
    private val combatBackgroundsData: CombatBackgroundsData
) {

    suspend fun getSuperHeroListByName(query: String): List<SuperHeroItem> {
        return superHeroService.getSuperHeroList(query)
    }

    fun setCombatDataScreen(player: SuperHeroItem, com: SuperHeroItem , background: Background) {
        combatDataScreen.playerCharacter = player
        combatDataScreen.comCharacter = com
        combatDataScreen.background = background

    }

    fun getCombatDataScreen(): CombatDataScreen {
        return combatDataScreen
    }

    fun setHeroDetail(hero:SuperHeroItem){
        heroDetail.superHeroDetail = hero
    }

    fun getHeroDetail(): SuperHeroItem? {
        return heroDetail.superHeroDetail
    }

    fun getCombatBackgroundData():List<Background>{
        return combatBackgroundsData.combatBackgroundsData
    }
}