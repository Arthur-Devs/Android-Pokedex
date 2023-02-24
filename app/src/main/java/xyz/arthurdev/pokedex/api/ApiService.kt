package xyz.arthurdev.pokedex.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.arthurdev.pokedex.models.PokemonResponse
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import xyz.arthurdev.pokedex.`object`.Pokemon
import xyz.arthurdev.pokedex.`object`.PokemonRequest

object ApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: PokemonApi = retrofit.create(PokemonApi::class.java)

    suspend fun list(limit: Int,offset: Int): PokemonResponse{
        return service.getPokemons(limit,offset)
    }

    suspend fun getPokemonByName(name: String): SinglePokemonResponse{
        return service.getSinglePokemon(name);
    }
}
