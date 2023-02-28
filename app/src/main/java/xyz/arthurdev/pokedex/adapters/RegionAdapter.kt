package xyz.arthurdev.pokedex.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.models.Region
import java.util.*

class RegionAdapter() : RecyclerView.Adapter<RegionAdapter.ViewHolder>() {
    private var regions: SortedMap<Int, Region> = sortedMapOf();

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var regionName: TextView

        init {
            regionName = itemView.findViewById(R.id.region_name)
        }

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.region_card_view, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.regionName.text = regions[position+1]?.name ?: "default region"
    }


    override fun getItemCount(): Int {
        return regions.size
    }

    fun setRegion(newRegions: SortedMap<Int, Region>){
        regions =newRegions
        notifyDataSetChanged()
    }

}