package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Sprites(
    val back_default: String?,
    val back_shiny: String?,
    val front_default: String,
    val front_shiny: String?
): Serializable