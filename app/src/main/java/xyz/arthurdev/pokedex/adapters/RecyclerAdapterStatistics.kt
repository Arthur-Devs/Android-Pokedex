package xyz.arthurdev.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.models.Stats

class RecyclerAdapterStatistics() : RecyclerView.Adapter<RecyclerAdapterStatistics.ViewHolder>() {
    private var stats: List<Stats> = ArrayList();

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var statName: TextView
        var baseStatValue: TextView
        var effortStatValue: TextView

        init {
            statName = itemView.findViewById(R.id.pokemonStat_name)
            baseStatValue = itemView.findViewById(R.id.pokemonStat_baseStat)
            effortStatValue = itemView.findViewById(R.id.pokemonStat_effort)
        }

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_pokemon_stats, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.statName.text = stats.elementAt(i).stat.name
        viewHolder.baseStatValue.text = stats.elementAt(i).base_stat.toString()
        viewHolder.effortStatValue.text = stats.elementAt(i).effort.toString()
    }

    override fun getItemCount(): Int {
        return stats.size
    }

    fun addStats(statList: List<Stats>){
        stats =statList
        notifyDataSetChanged()
    }

}