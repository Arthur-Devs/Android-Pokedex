package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.databinding.FramentPokemonDetailBinding

class PokemonDetail : Fragment(R.layout.frament_pokemon_detail) {
    val args: PokemonDetailArgs by navArgs()
    private var fragmentBinding: FramentPokemonDetailBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frament_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FramentPokemonDetailBinding.bind(view)
        fragmentBinding = binding
        val pokemon = args.pokemon
        binding.pokemonDetailName.text = pokemon.name

        Picasso.with(binding.pokemonDetailImage.context).load(pokemon.sprites.front_default).into(binding.pokemonDetailImage)
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        fragmentBinding = null
        super.onDestroyView()
    }
}

