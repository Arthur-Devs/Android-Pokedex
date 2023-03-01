package xyz.arthurdev.pokedex.repository

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import xyz.arthurdev.pokedex.api.ApiService
import xyz.arthurdev.pokedex.models.ItemModelApi
import xyz.arthurdev.pokedex.models.NeighborPokemon
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import java.util.*

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

    private suspend fun fetchPokemons(limit:Int,offset:Int): List<SinglePokemonResponse> {
        val toIndex: Int = if(offset+limit > count.get()) count.get() else offset +limit
        if (offset > toIndex || offset < 0 || offset> pokemonItemList.size) return listOf()

        var listOfPokemon = pokemonItemList.subList(offset,toIndex)
        listOfPokemon = listOfPokemon.filter { !pokemonList.containsKey(it.name)  }
        val list = mutableListOf<SinglePokemonResponse>()
        for (pokemon in listOfPokemon) {
            pokemonList[pokemon.name] = fetchPokemon(pokemon.name)
            list.add(pokemonList[pokemon.name]!!)
        }
        return list
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

    suspend fun getPokemons(limit:Int,offset:Int): List<SinglePokemonResponse> {
        val list = fetchPokemons(limit,offset)
        return list
            .sortedBy { it.id }
    }

    suspend fun getPokemonsWithFilter(limit:Int,offset:Int,filter:String): List<SinglePokemonResponse> {
        var list =pokemonItemList.filter { it.name.contains(filter) }


        val toIndex: Int = if(offset+limit > list.size) list.size else offset +limit
        if (offset > toIndex || offset < 0 || offset> pokemonItemList.size) return listOf()

        list =list.subList(offset,toIndex)
        var filteredPokemonList:MutableList<SinglePokemonResponse> = mutableListOf()
        for (pokemon in list) {
            pokemonList[pokemon.name] = fetchPokemon(pokemon.name)
            filteredPokemonList.add(pokemonList[pokemon.name]!!)
        }

        return filteredPokemonList.sortedBy { it.id }
    }

    suspend fun getPokemonNeighbor(pokemon: SinglePokemonResponse):NeighborPokemon {
        val previous = if(pokemon.id > 1) fetchPokemon(pokemon.id - 1) else null
        val next = if(pokemon.id < count.get()) fetchPokemon(pokemon.id + 1) else null
        return NeighborPokemon(previous,next)
    }
}