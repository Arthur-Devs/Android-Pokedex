package xyz.arthurdev.pokedex.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.arthurdev.pokedex.models.Region
import xyz.arthurdev.pokedex.repository.RegionRepository
import java.util.*

class RegionViewModel: ViewModel() {
    private val service = RegionRepository
    private val _pokemonMutableLiveData = MutableLiveData<SortedMap<Int,Region>>()
    val pokemonLiveDate: LiveData<SortedMap<Int,Region>> = _pokemonMutableLiveData

    fun loadRegion(){
        viewModelScope.launch {
            val series= service.getRegions(10,0)
            val map: MutableMap<Int, Region> = _pokemonMutableLiveData.value ?: mutableMapOf()
            map.putAll(series)
            _pokemonMutableLiveData.postValue(map.toSortedMap())
        }
    }

}