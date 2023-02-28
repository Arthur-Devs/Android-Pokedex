package xyz.arthurdev.pokedex.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.arthurdev.pokedex.models.ListModelAPI
import xyz.arthurdev.pokedex.models.Region
import xyz.arthurdev.pokedex.models.SinglePokemonResponse

interface PokemonApi {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): ListModelAPI

    @GET("pokemon/{name}/")
    suspend fun getSinglePokemon(
        @Path("name") name: String
    ): SinglePokemonResponse

    @GET("region")
    suspend fun getRegions(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): ListModelAPI

    @GET("region/{id}/")
    suspend fun getRegion(@Path("id") id: Int):Region
}