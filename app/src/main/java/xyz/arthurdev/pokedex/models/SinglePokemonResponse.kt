package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int,
    val name: String,
    val id: Int
)