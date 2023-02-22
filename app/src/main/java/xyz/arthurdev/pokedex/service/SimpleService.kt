package xyz.arthurdev.pokedex.service

import retrofit2.http.GET;
import xyz.arthurdev.pokedex.`object`.Pokemon
import xyz.arthurdev.pokedex.`object`.PokemonRequest

interface SimpleService {
    @GET("pokemon?limit=100000&offset=0")
    suspend fun listAll(): PokemonRequest
}