package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroDetail @Inject constructor() {

    var superHeroDetail : SuperHeroItem? = null
}