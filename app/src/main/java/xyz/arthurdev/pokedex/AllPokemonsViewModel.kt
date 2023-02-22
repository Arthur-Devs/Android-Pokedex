package xyz.arthurdev.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.arthurdev.pokedex.`object`.Pokemon
import xyz.arthurdev.pokedex.service.ApiService

class AllPokemonsViewModel() : ViewModel() {
    private val service = ApiService;
    private val _listAllPokemons = MutableLiveData<List<Pokemon>>()
    val listAllPokemons: LiveData<List<Pokemon>> = _listAllPokemons

    fun fetchAllPokemons() {
        viewModelScope.launch {
            val pokemons = service.listAll()
            _listAllPokemons.postValue(pokemons.results)
        }
    }
}
