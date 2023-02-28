package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.RecyclerAdapter
import xyz.arthurdev.pokedex.databinding.FragmentHomeBinding
import xyz.arthurdev.pokedex.databinding.FragmentPokemonListBinding
import xyz.arthurdev.pokedex.viewModel.NewPokemonViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [PokemonList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonList : Fragment() {


    private var loading = true
    private var page = 0

    private lateinit var pokemonViewModel: NewPokemonViewModel
    private lateinit var adapter: RecyclerAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemonViewModel = ViewModelProvider(this)[NewPokemonViewModel::class.java]
        pokemonViewModel.pokemonLiveDate.observe(this) { pokemons ->
            adapter.addPokemon(pokemons)
            loading = false;
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokemonViewModel.loadPokemons(page)
        page++

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val binding = FragmentPokemonListBinding.bind(itemView)
        binding.recycleView.layoutManager=LinearLayoutManager(context)

        adapter = RecyclerAdapter()
        binding.recycleView.adapter = adapter

        binding.recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val scrollRemaining = recyclerView.computeVerticalScrollRange()-recyclerView.computeVerticalScrollOffset()-recyclerView.computeVerticalScrollExtent()
                if (scrollRemaining < 1500 && !loading) {
                        loading = true
                        pokemonViewModel.loadPokemons(page)
                        page++

                }
            }
        })

    }
}