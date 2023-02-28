package xyz.arthurdev.pokedex.models

data class NeighborPokemon(
    val previous: SinglePokemonResponse?,
    val next: SinglePokemonResponse?
)
