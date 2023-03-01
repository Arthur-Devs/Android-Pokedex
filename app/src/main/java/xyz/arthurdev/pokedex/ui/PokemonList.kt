package xyz.arthurdev.pokedex.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.adapters.RecyclerAdapter
import xyz.arthurdev.pokedex.databinding.FragmentPokemonListBinding
import xyz.arthurdev.pokedex.viewModel.NewPokemonViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [PokemonList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PokemonList : Fragment() {
    private var loading = false
    private var page = 0
    private var noFilter = true
    private var searchFor = ""

    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var pokemonViewModel: NewPokemonViewModel
    private lateinit var adapter: RecyclerAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPokemonListBinding.inflate(layoutInflater)

        pokemonViewModel = ViewModelProvider(this)[NewPokemonViewModel::class.java]
        pokemonViewModel.pokemonLiveDate.observe(this) { pokemons ->
            adapter.addPokemon(pokemons)
            Log.d("pokemonList", pokemons.size.toString())
            loading = false;
        }

    }

    private fun loadPokemons(searchChange: Boolean = false) {
        if (loading) return
        loading = true
        Log.d("pokemonList", binding.searchBar.text.toString())
        Log.d("pokemonList", noFilter.toString())
        if(binding.searchBar.text.toString().isNotEmpty()) {
            if (noFilter) noFilter = false

            if (searchChange) {
                page = 0
                adapter.clear()
            }
            pokemonViewModel.loadPokemonWithFilter(page, binding.searchBar.text.toString())
        }else{
            if (!noFilter) {
                noFilter = true
                page = 0
                adapter.clear()
            }
            Log.d("pokemonList", page.toString())
            pokemonViewModel.loadPokemons(page)
            Log.d("pokemonList", "loadPokemons")
        }
        page++
    }

    private fun onSearchTextChanged(binding: FragmentPokemonListBinding, context:Context) {
        //debounce
        binding.searchBar.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {
                // Do nothing
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                Log.d("pokemonList", searchText)
                Log.d("pokemonList", searchFor)
                if (searchText == searchFor)
                    return

                searchFor = searchText
                //launch
                CoroutineScope(Job()).launch {
                    delay(200)
                    if (searchFor == searchText) {
                        Log.d("pokemonList", "loadPokemons")
                        Log.d("pokemonList", searchText)
                        Log.d("pokemonList", searchFor)
                        loadPokemons(true)
                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        page=0
        loadPokemons()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        binding = FragmentPokemonListBinding.bind(itemView)
        binding.recycleView.layoutManager=LinearLayoutManager(context)

        onSearchTextChanged(binding,binding.recycleView.context)
        page=0
        loadPokemons()
        adapter = RecyclerAdapter()
        binding.recycleView.adapter = adapter

        binding.recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val scrollRemaining = recyclerView.computeVerticalScrollRange()-recyclerView.computeVerticalScrollOffset()-recyclerView.computeVerticalScrollExtent()
                if (scrollRemaining < 500 && !loading) {
                    loadPokemons()

                }
            }
        })

    }
}