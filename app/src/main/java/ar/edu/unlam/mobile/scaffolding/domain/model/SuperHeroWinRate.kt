package ar.edu.unlam.mobile.scaffolding.domain.model

import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroEntity
import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroOfflineEntity
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroImage
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem

class SuperHeroWinRate(val name: String, val image: String, var winRate: Int) {
    fun toEntity() = SuperHeroEntity(name = name, image = image, winRate = winRate)
}

fun SuperHeroEntity.toDomain() = SuperHeroWinRate(name, image, winRate)



