package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Region(
    val id: Int,
    val pokedexes: List<ItemModelApi>,
    val name: String
)
