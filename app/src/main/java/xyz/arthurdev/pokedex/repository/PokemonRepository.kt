package xyz.arthurdev.pokedex.repository

import androidx.annotation.RestrictTo
import kotlinx.coroutines.*
import xyz.arthurdev.pokedex.api.ApiService
import xyz.arthurdev.pokedex.models.ItemModelApi
import xyz.arthurdev.pokedex.models.ListModelAPI
import xyz.arthurdev.pokedex.models.Region
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import java.util.*
import java.util.stream.Collectors

object PokemonRepository {
    private var count:Optional<Int> = Optional.empty();
    private lateinit var pokemonItemList: List<ItemModelApi>
    private var pokemonList:MutableMap<String,SinglePokemonResponse> = mutableMapOf()
    private val apiService = ApiService

    //constructor
    init {
        runBlocking {
            launch {
                fetchPokemons()
            }
        }
    }

    private suspend fun fetchPokemons() {
        pokemonItemList = apiService.getPokemons(10000,0).results
        count = Optional.of(pokemonItemList.size)
    }

    private suspend fun fetchPokemons(limit:Int,offset:Int) {
            val listOfPokemon = pokemonItemList.subList(offset,offset+limit)
            listOfPokemon.filter { !pokemonList.containsKey(it.name)  }.forEachIndexed { index, pokemon: ItemModelApi ->
                pokemonList[pokemon.name] = fetchPokemon(index + offset + 1)
            }
    }

    private suspend fun fetchPokemon(id: Int):SinglePokemonResponse {
        val pokemon = apiService.getPokemon(id)
        pokemonList[pokemon.name] = pokemon
        return pokemon
    }
    private suspend fun fetchPokemon(name: String):SinglePokemonResponse {
        val pokemon = apiService.getPokemon(name)
        pokemonList[pokemon.name] = pokemon
        return pokemon
    }

    suspend fun getPokemons(limit:Int,offset:Int): Map<String,SinglePokemonResponse> {
        fetchPokemons(limit,offset)

        return pokemonList.toList().subList(offset,offset+limit)
            .sortedBy { (_, value) -> value.id }
            .toMap()
    }

    suspend fun getPokemonsWithFilter(limit:Int,offset:Int,filter:String): Map<String,SinglePokemonResponse> {
        pokemonItemList.filter { it.name.contains(filter) }.subList(offset,offset+limit).forEach { pokemon: ItemModelApi ->
            pokemonList[pokemon.name] = fetchPokemon(pokemon.name)
        }
        return pokemonList.filter { it.key.contains(filter) }
            .toList().subList(offset,offset+limit)
            .sortedBy { (_, value) -> value.id }
            .toMap()
    }
}