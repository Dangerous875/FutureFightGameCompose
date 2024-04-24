package ar.edu.unlam.mobile.scaffolding.data.local.model

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val isSuccess: String,
    @SerializedName("results") val superheroes: List<SuperHeroItem>
)
