package xyz.arthurdev.pokedex

import android.os.Bundle
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
import xyz.arthurdev.pokedex.ui.PokemonListDirections


class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var pokemons: List<SinglePokemonResponse> = ArrayList();

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var pokemonName: TextView
        var pokemonImage: ImageView
        var card: CardView

        init {
            pokemonName = itemView.findViewById(R.id.pokemon_name)
            pokemonImage = itemView.findViewById(R.id.pokemon_sprite)
            card = itemView.findViewById(R.id.card)
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
        viewHolder.card.setOnClickListener {
            val bundle = Bundle()
            val pokemon = pokemons.elementAt(i)
            bundle.putSerializable("MyData", pokemon)
            val action = PokemonListDirections.actionHomeToPokemonDetail(pokemon)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    fun addPokemon(pokemonList: List<SinglePokemonResponse>){
        pokemons =pokemonList
        notifyDataSetChanged()
    }

}