package ar.edu.unlam.mobile.scaffolding.domain.model

import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroEntity
import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroOfflineEntity
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroImage
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem

class SuperHeroWinRate(val name: String, val image: String, var winRate: Int) {
    fun toEntity() = SuperHeroEntity(name = name, image = image, winRate = winRate)
}

fun SuperHeroEntity.toDomain() = SuperHeroWinRate(name, image, winRate)

fun SuperHeroItem.toEntity() = SuperHeroOfflineEntity(
    name = this.name,
    powerstats = this.powerstats,
    biography = this.biography,
    appearance = this.appearance,
    image = SuperHeroImage("https://i.pinimg.com/236x/be/53/18/be5318244b45bf2c4e8aac3297b58e40.jpg")
)

fun SuperHeroOfflineEntity.toSuperHeroItem() = SuperHeroItem(
    id = this.id.toString(),
    name = this.name,
    powerstats = this.powerstats,
    biography = this.biography,
    appearance = this.appearance,
    image = this.image
)