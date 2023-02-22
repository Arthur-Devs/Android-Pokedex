package xyz.arthurdev.pokedex.service

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.arthurdev.pokedex.`object`.Pokemon
import xyz.arthurdev.pokedex.`object`.PokemonRequest

object ApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: SimpleService = retrofit.create(SimpleService::class.java)

    suspend fun listAll(): PokemonRequest{
        val test=service.listAll();
        Log.d("Test 2",test.toString())
        return test;
    }
}
