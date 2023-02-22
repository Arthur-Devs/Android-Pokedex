package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.arthurdev.pokedex.AllPokemonsViewModel
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.service.SimpleService

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {

    private lateinit var pokemonViewModel: AllPokemonsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pokemonViewModel = ViewModelProvider(this)[AllPokemonsViewModel::class.java]
        pokemonViewModel.listAllPokemons.observe(this) { pokemons ->
            Log.d("Home", "Liste des pokémons : $pokemons")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}