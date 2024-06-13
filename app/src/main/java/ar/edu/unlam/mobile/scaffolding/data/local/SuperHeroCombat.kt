package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroImage

data class SuperHeroCombat(
    val id: String,
    val name: String,
    var attack: Int,
    var defense: Int,
    var life: Int,
    var damageAbs: Int,
    var damagePenance : Int,
    var healingPotion: Int,
    val image: SuperHeroImage
)
