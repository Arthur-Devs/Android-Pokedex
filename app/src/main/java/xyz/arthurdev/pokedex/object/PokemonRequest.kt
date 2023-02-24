package xyz.arthurdev.pokedex.`object`

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonRequest (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon> = ArrayList<Pokemon>()
)