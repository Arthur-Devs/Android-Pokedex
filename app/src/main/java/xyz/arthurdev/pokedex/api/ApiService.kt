package xyz.arthurdev.pokedex.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.arthurdev.pokedex.models.ListModelAPI
import xyz.arthurdev.pokedex.models.Region
import xyz.arthurdev.pokedex.models.SinglePokemonResponse

object ApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: PokemonApi = retrofit.create(PokemonApi::class.java)

    suspend fun getPokemon(name:String): SinglePokemonResponse{
        return service.getPokemon(name)
    }
    suspend fun getPokemon(int:Int):SinglePokemonResponse{
        return service.getPokemon(int)
    }

    suspend fun getPokemons(limit: Int,offset: Int): ListModelAPI{
        return service.getPokemons(limit,offset)
    }



    suspend fun getRegions(limit: Int,offset: Int): ListModelAPI{
        return service.getRegions(limit,offset)
    }

    suspend fun getRegion(id: Int): Region{
        return service.getRegion(id)
    }
}
