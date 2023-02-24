package xyz.arthurdev.pokedex.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.arthurdev.pokedex.models.PokemonResponse
import xyz.arthurdev.pokedex.models.SinglePokemonResponse

interface PokemonApi {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): PokemonResponse

    @GET("pokemon/{name}/")
    suspend fun getSinglePokemon(
        @Path("name") name: String
    ): SinglePokemonResponse
}