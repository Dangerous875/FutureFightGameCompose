package ar.edu.unlam.mobile.scaffolding.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroAppearance
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroBiography
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroImage
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroPowerStats

@Entity(tableName = "Hero_table")
data class SuperHeroOfflineEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "powerstats") val powerstats: SuperHeroPowerStats,
    @ColumnInfo(name = "biography") val biography: SuperHeroBiography,
    @ColumnInfo(name = "appearance") val appearance: SuperHeroAppearance,
    @ColumnInfo(name = "image") val image: SuperHeroImage
)

fun SuperHeroItem.toEntity() = SuperHeroOfflineEntity(
    name = this.name,
    powerstats = this.powerstats,
    biography = this.biography,
    appearance = this.appearance,
    image = SuperHeroImage("https://cloudfront-us-east-1.images.arcpublishing.com/metroworldnews/L77YO5TKDFDMFK4XHF667ARZ24.jpg")
)