package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Stat(
    val name: String,
    val url: String
): Serializable