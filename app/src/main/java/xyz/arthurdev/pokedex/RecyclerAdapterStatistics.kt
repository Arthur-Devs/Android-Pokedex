package xyz.arthurdev.pokedex

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import xyz.arthurdev.pokedex.models.Stat
import xyz.arthurdev.pokedex.models.Stats
import xyz.arthurdev.pokedex.ui.Home
import xyz.arthurdev.pokedex.ui.HomeDirections
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


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
            .inflate(R.layout.card_view, viewGroup, false)
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

    fun addPokemon(statList: List<Stats>){
        stats =statList
        notifyDataSetChanged()
    }

}