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
import xyz.arthurdev.pokedex.databinding.FragmentHomeBinding
import xyz.arthurdev.pokedex.viewModel.PokemonViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [PokemonList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonList : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var loading = true;

    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapter: RecyclerAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        pokemonViewModel = ViewModelProvider(this)[PokemonViewModel::class.java]
        pokemonViewModel.pokemonLiveDate.observe(this) { pokemons ->
            adapter.addPokemon(pokemons)
            loading = false;
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokemonViewModel.loadNextPokemon()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycle_view)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager

        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val scrollRemaining = recyclerView.computeVerticalScrollRange()-recyclerView.computeVerticalScrollOffset()-recyclerView.computeVerticalScrollExtent()
                if (scrollRemaining < 1500 && !loading) {
                    loading = true
                    pokemonViewModel.loadNextPokemon()
                }
            }
        })

    }
}