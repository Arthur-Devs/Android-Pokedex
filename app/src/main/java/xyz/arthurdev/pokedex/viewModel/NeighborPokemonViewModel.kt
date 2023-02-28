package xyz.arthurdev.pokedex.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.arthurdev.pokedex.models.NeighborPokemon
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import xyz.arthurdev.pokedex.repository.PokemonRepository

class NeighborPokemonViewModel: ViewModel(){
    private val pokemonRepository = PokemonRepository

    private val _pokemonNeighborMutableLiveData = MutableLiveData<NeighborPokemon>()
    val pokemonNeighborLiveDate: LiveData<NeighborPokemon> = _pokemonNeighborMutableLiveData

    suspend fun loadNeighborPokemon(pokemon: SinglePokemonResponse){
        _pokemonNeighborMutableLiveData.postValue(pokemonRepository.getPokemonNeighbor(pokemon))
    }
}