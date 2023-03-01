package xyz.arthurdev.pokedex.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.arthurdev.pokedex.models.Region
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import xyz.arthurdev.pokedex.repository.PokemonRepository
import xyz.arthurdev.pokedex.repository.RegionRepository
import java.util.*

class NewPokemonViewModel: ViewModel() {
    val limit = 10
    private val service = PokemonRepository
    private val _pokemonMutableLiveData = MutableLiveData<List< SinglePokemonResponse>>()
    val pokemonLiveDate: LiveData<List< SinglePokemonResponse>> = _pokemonMutableLiveData

    fun loadPokemons(page: Int){
        viewModelScope.launch {
            val series= service.getPokemons(limit,limit*page)
            val list: MutableList<SinglePokemonResponse> =  series.toMutableList()
            _pokemonMutableLiveData.postValue(list.sortedBy { it.id })
        }
    }

    fun loadPokemonWithFilter(page:Int,filter:String){
        viewModelScope.launch {
            val series= service.getPokemonsWithFilter(limit,limit*page,filter)
            val list: MutableList<SinglePokemonResponse> =  series.toMutableList()
            _pokemonMutableLiveData.postValue(
           list.sortedBy { it.id }
            )
        }
    }

}