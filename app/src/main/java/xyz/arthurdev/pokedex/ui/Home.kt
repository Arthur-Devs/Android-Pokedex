package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.RecyclerAdapter
import xyz.arthurdev.pokedex.databinding.FragmentHomeBinding
import xyz.arthurdev.pokedex.`object`.Pokemon
import xyz.arthurdev.pokedex.viewModel.PokemonViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var pokemonList: List<Pokemon> = ArrayList();

    private lateinit var pokemonViewModel: PokemonViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        pokemonViewModel.pokemonLiveDate.observe(this) { pokemons ->
            Log.d("test",pokemons.toString())
            adapter.addPokemon(pokemons)
        }
        pokemonViewModel.loadNextPokemon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycle_view)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

    }
}