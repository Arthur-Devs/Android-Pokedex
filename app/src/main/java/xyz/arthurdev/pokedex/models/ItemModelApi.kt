package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemModelApi(
    val name: String,
    val url: String
)