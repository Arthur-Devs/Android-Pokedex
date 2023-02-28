package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.databinding.FragmentHomeBinding
import xyz.arthurdev.pokedex.databinding.FramentPokemonDetailBinding

class Home: Fragment(R.layout.fragment_home) {

    private lateinit var binding :FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.homeListPokemonButton.setOnClickListener{
            it.findNavController().navigate(HomeDirections.actionHomeToPokemonListFragment())
        }
        binding.homeListPokemonByRegionButton.setOnClickListener{
            it.findNavController().navigate(HomeDirections.actionHomeToRegionListFragment())
        }
    }
}