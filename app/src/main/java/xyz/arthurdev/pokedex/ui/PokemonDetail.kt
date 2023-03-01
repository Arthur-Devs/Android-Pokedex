package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.databinding.FramentPokemonDetailBinding
import xyz.arthurdev.pokedex.adapters.RecyclerAdapterStatistics
import xyz.arthurdev.pokedex.models.SinglePokemonResponse
import xyz.arthurdev.pokedex.viewModel.NeighborPokemonViewModel

class PokemonDetail : Fragment(R.layout.frament_pokemon_detail) {
    val args: PokemonDetailArgs by navArgs()

    private var shiny = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frament_pokemon_detail, container, false)
    }


    private fun getNeighborPokemon(pokemon: SinglePokemonResponse, binding: FramentPokemonDetailBinding){
        val pokemonNeighborViewModel = ViewModelProvider(this)[NeighborPokemonViewModel::class.java]
        pokemonNeighborViewModel.pokemonNeighborLiveDate.observe(viewLifecycleOwner) { neighborPokemon ->

            if(neighborPokemon.previous != null){

                binding.previousPokemon.visibility = View.VISIBLE
                binding.previousPokemonName.text = neighborPokemon.previous.name
                binding.previousPokemon.setOnClickListener {
                    view?.findNavController()?.navigate(PokemonDetailDirections.actionPokemonDetailSelf(neighborPokemon.previous))
            }}
            if(neighborPokemon.next != null){
                binding.nextPokemon.visibility = View.VISIBLE
                binding.nextPokemonName.text = neighborPokemon.next.name
                binding.nextPokemon.setOnClickListener {
                    view?.findNavController()?.navigate(PokemonDetailDirections.actionPokemonDetailSelf(neighborPokemon.next))
            }}
        }
        lifecycleScope.launch {
            pokemonNeighborViewModel.loadNeighborPokemon(pokemon)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentBinding = FramentPokemonDetailBinding.bind(view)
        val pokemon = args.pokemon
        getNeighborPokemon(pokemon,fragmentBinding)

        fragmentBinding.pokemonDetailStats.layoutManager=LinearLayoutManager(context)


        fragmentBinding.pokemonDetailName.text = pokemon.name
        fragmentBinding.pokemonDetailPhysicalAttributes.text = "Height: ${pokemon.height} - Weight: ${pokemon.weight}"
        fragmentBinding.pokemonDetailTypes.text = "Types: ${pokemon.types.map { it.type.name }.joinToString(" - ")}"

        val adapter = RecyclerAdapterStatistics()
        adapter.addStats(pokemon.stats)
        fragmentBinding.pokemonDetailStats.adapter = adapter

        Picasso.with(fragmentBinding.pokemonDetailImage.context).load(pokemon.sprites.front_default).into(fragmentBinding.pokemonDetailImage)
        fragmentBinding.pokemonDetailImage.setOnClickListener{
            shiny = !shiny
            val image = if(shiny) pokemon.sprites.front_shiny else pokemon.sprites.front_default

            Picasso.with(fragmentBinding.pokemonDetailImage.context).load(image).into(fragmentBinding.pokemonDetailImage)
        }
    }
}

