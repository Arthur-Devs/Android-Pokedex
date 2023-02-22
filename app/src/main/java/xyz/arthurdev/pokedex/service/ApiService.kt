package xyz.arthurdev.pokedex.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.arthurdev.pokedex.`object`.Pokemon

object ApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: SimpleService = retrofit.create(SimpleService::class.java)

    suspend fun listAll(): List<Pokemon> {
        return service.listAll();
    }
}
