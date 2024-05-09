package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CombatDataScreen @Inject constructor() {

    var playerCharacter : SuperHeroItem? = null
    var comCharacter : SuperHeroItem? = null
    var background : Background? = null

}