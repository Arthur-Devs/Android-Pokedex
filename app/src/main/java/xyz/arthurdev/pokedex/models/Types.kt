package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Types(
    val slot: Int,
    val type: Stat
): Serializable