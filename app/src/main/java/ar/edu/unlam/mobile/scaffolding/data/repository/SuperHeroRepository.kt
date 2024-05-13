package ar.edu.unlam.mobile.scaffolding.data.repository

import ar.edu.unlam.mobile.scaffolding.data.local.Background
import ar.edu.unlam.mobile.scaffolding.data.local.CombatBackgroundsData
import ar.edu.unlam.mobile.scaffolding.data.local.CombatDataScreen
import ar.edu.unlam.mobile.scaffolding.data.local.HeroDetail
import ar.edu.unlam.mobile.scaffolding.data.local.ResultData
import ar.edu.unlam.mobile.scaffolding.data.local.ResultDataScreen
import ar.edu.unlam.mobile.scaffolding.data.local.SuperHeroCombat
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.network.service.SuperHeroService
import javax.inject.Inject

class SuperHeroRepository @Inject constructor(
    private val superHeroService: SuperHeroService,
    private val combatDataScreen: CombatDataScreen,
    private val heroDetail: HeroDetail,
    private val combatBackgroundsData: CombatBackgroundsData,
    private val resultDataScreen: ResultDataScreen
) {

    suspend fun getSuperHeroListByName(query: String): List<SuperHeroItem> {
        return superHeroService.getSuperHeroList(query)
    }

    fun setCombatDataScreen(player: SuperHeroCombat, com: SuperHeroCombat , background: Background) {
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

    fun setResultDataScreen(superHeroPlayer : SuperHeroCombat , superHeroCom : SuperHeroCombat){
        resultDataScreen.resultDataScreen = ResultData(superHeroPlayer,superHeroCom)

    }

    fun getResultDataScreen():ResultDataScreen{
        return resultDataScreen
    }
}