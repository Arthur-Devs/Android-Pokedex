package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.databinding.FragmentHomeBinding

class Home: Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        binding.homeListPokemonButton.setOnClickListener{
            it.findNavController().navigate(HomeDirections.actionHomeToPokemonListFragment())
        }
        binding.homeListPokemonByRegionButton.setOnClickListener{
            it.findNavController().navigate(HomeDirections.actionHomeToRegionListFragment())
        }
    }
}