package ar.edu.unlam.mobile.scaffolding.data.local.model

import com.google.gson.annotations.SerializedName

data class SuperHeroPowerStats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)


