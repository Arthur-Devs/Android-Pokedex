package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.RecyclerAdapter
import xyz.arthurdev.pokedex.databinding.FragmentSearchBinding
import xyz.arthurdev.pokedex.viewModel.PokemonSearchViewModel

class Search : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var pokemonSearchViewModel: PokemonSearchViewModel
    private lateinit var adapter: RecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(layoutInflater)

        pokemonSearchViewModel = ViewModelProvider(this)[PokemonSearchViewModel::class.java]
        pokemonSearchViewModel.pokemonLiveDate.observe(this) { pokemons ->
            adapter.addPokemon(pokemons)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokemonSearchViewModel.loadAllPokemons()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
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