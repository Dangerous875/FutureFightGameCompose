package ar.edu.unlam.mobile.scaffolding.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CombatDataScreen @Inject constructor() {

    var playerCharacter : SuperHeroCombat? = null
    var comCharacter : SuperHeroCombat? = null
    var background : Background? = null

}