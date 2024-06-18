package ar.edu.unlam.mobile.scaffolding.data.local.model

import com.google.gson.annotations.SerializedName

data class SuperHeroBiography(
    @SerializedName("full-name") var fullName: String,
    @SerializedName("first-appearance") val firstAppearance: String,
    @SerializedName("publisher") var publisher: String,
    @SerializedName("alignment") val alignment: String
)
