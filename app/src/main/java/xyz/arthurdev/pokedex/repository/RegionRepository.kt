package xyz.arthurdev.pokedex.repository

import android.util.Log
import xyz.arthurdev.pokedex.api.ApiService
import xyz.arthurdev.pokedex.models.ItemModelApi
import xyz.arthurdev.pokedex.models.ListModelAPI
import xyz.arthurdev.pokedex.models.Region
import java.util.Optional
import java.util.SortedMap

object RegionRepository {

    private var count:Optional<Int> = Optional.empty();
    private val regionItemList: MutableMap<Int,ItemModelApi> = mutableMapOf()
    private var regionList:MutableMap<Int,Region> = mutableMapOf()
    private val apiService = ApiService

    private suspend fun fetchRegions(limit:Int,offset:Int) {
        if(count.isPresent && offset< count.get()) return
        val range = (offset+1..offset+limit+1).toList()
        if(regionItemList.keys.containsAll(range)) return

        val listOfRegion : ListModelAPI = apiService.getRegions(limit,offset)
        count = Optional.of(listOfRegion.count)
        listOfRegion.results.forEachIndexed {index,region:ItemModelApi->
            regionItemList[index+offset+1]=region
            regionList[index+offset+1] = fetchRegion(index+offset+1)
        }
    }

    private suspend fun fetchRegion(id: Int):Region {
        val region = apiService.getRegion(id)
        regionList[id] = region
        return region
    }

    suspend fun getRegion(id: Int):Region{
        return regionList.getOrDefault(id, fetchRegion(id)).copy()
    }

    suspend fun getRegions(limit:Int,offset:Int): SortedMap<Int,Region> {
        fetchRegions(limit,offset)

        return regionList.toSortedMap().subMap(offset+1,offset+limit+1)
    }

}