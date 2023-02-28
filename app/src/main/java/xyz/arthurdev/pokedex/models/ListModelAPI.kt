package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListModelAPI(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ItemModelApi>
)