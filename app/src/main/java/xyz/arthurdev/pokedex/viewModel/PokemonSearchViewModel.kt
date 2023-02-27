package xyz.arthurdev.pokedex.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.arthurdev.pokedex.api.ApiService
import xyz.arthurdev.pokedex.models.SinglePokemonResponse

class PokemonSearchViewModel() : ViewModel() {
    private var page = 0;
    private var limit: Int? = null;
    private val service = ApiService
    private val _pokemonMutableLiveData = MutableLiveData<List<SinglePokemonResponse>>()
    val pokemonLiveDate: LiveData<List<SinglePokemonResponse>> = _pokemonMutableLiveData

    fun loadNextPokemon() {
        viewModelScope.launch {
            val pokemons = service.listAll()
            val list = ArrayList<SinglePokemonResponse>()

            pokemons.results.forEach {
                list.plusAssign(service.getPokemonByName(it.name))
            }

            _pokemonMutableLiveData.postValue(list)
        }
    }
}