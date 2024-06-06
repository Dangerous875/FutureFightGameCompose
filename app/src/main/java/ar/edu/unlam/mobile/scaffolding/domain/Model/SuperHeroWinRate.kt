package ar.edu.unlam.mobile.scaffolding.domain.Model

import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroEntity

class SuperHeroWinRate (val name:String, val image:String, var winRate:Int){
    fun toEntity()=SuperHeroEntity(name=name, image = image, winRate = winRate)
}
fun SuperHeroEntity.toDomain()=SuperHeroWinRate(name,image,winRate)