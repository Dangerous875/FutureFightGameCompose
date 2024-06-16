package ar.edu.unlam.mobile.scaffolding.data.local.model

import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroOfflineEntity

data class SuperHeroItem(
    val id: String,
    val name: String,
    var powerstats: SuperHeroPowerStats,
    val biography : SuperHeroBiography,
    val appearance : SuperHeroAppearance,
    val image : SuperHeroImage,
    var imagePath: String? = null
)

fun SuperHeroOfflineEntity.toSuperHeroItem() = SuperHeroItem(
    id = this.id.toString(),
    name = this.name,
    powerstats = this.powerstats,
    biography = this.biography,
    appearance = this.appearance,
    image = this.image,
    imagePath = this.imagePath
)
