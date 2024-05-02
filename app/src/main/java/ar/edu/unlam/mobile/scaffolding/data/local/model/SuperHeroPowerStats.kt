package ar.edu.unlam.mobile.scaffolding.data.local.model

import com.google.gson.annotations.SerializedName

data class SuperHeroPowerStats(
    @SerializedName("intelligence") var intelligence: String,
    @SerializedName("strength") var strength: String,
    @SerializedName("speed") var speed: String,
    @SerializedName("durability") var durability: String,
    @SerializedName("power") var power: String,
    @SerializedName("combat") var combat: String
)


