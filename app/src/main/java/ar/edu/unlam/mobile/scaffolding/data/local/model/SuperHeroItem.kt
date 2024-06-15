package ar.edu.unlam.mobile.scaffolding.data.local.model

data class SuperHeroItem(
    val id: String,
    val name: String,
    var powerstats: SuperHeroPowerStats,
    val biography : SuperHeroBiography,
    val appearance : SuperHeroAppearance,
    val image : SuperHeroImage,
    var imagePath: String? = null
)
