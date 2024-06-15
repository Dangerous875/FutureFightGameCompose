package ar.edu.unlam.mobile.scaffolding.data.database.converter

import androidx.room.TypeConverter
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroAppearance
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroBiography
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroImage
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroPowerStats
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromPowerStats(powerStats: SuperHeroPowerStats): String {
        return Gson().toJson(powerStats)
    }

    @TypeConverter
    fun toPowerStats(powerStatsString: String): SuperHeroPowerStats {
        val type = object : TypeToken<SuperHeroPowerStats>() {}.type
        return Gson().fromJson(powerStatsString, type)
    }

    @TypeConverter
    fun fromBiography(biography: SuperHeroBiography): String {
        return Gson().toJson(biography)
    }

    @TypeConverter
    fun toBiography(biographyString: String): SuperHeroBiography {
        val type = object : TypeToken<SuperHeroBiography>() {}.type
        return Gson().fromJson(biographyString, type)
    }

    @TypeConverter
    fun fromAppearance(appearance: SuperHeroAppearance): String {
        return Gson().toJson(appearance)
    }

    @TypeConverter
    fun toAppearance(appearanceString: String): SuperHeroAppearance {
        val type = object : TypeToken<SuperHeroAppearance>() {}.type
        return Gson().fromJson(appearanceString, type)
    }

    @TypeConverter
    fun fromImage(image: SuperHeroImage): String {
        return Gson().toJson(image)
    }

    @TypeConverter
    fun toImage(imageString: String): SuperHeroImage {
        val type = object : TypeToken<SuperHeroImage>() {}.type
        return Gson().fromJson(imageString, type)
    }
}