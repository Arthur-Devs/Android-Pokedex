package xyz.arthurdev.pokedex

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var pokemons: List<SinglePokemonResponse> = ArrayList();

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pokemonName: TextView
        var pokemonImage: ImageView

        init {
            pokemonName = itemView.findViewById(R.id.pokemon_name)
            pokemonImage = itemView.findViewById(R.id.pokemon_sprite)
        }

    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_view, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.pokemonName.text = pokemons.elementAt(i).name

        Picasso.with(viewHolder.pokemonImage.context).load(pokemons.elementAt(i).sprites.front_default).into(viewHolder.pokemonImage)

    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    fun addPokemon(pokemonList: List<SinglePokemonResponse>){
        val index = pokemons.size
        pokemons =pokemonList
        notifyDataSetChanged()
    }

}