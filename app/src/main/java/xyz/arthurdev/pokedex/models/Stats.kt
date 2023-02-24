package xyz.arthurdev.pokedex.models

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
): Serializable