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
import xyz.arthurdev.pokedex.adapters.RegionAdapter
import xyz.arthurdev.pokedex.databinding.FragmentRegionListBinding
import xyz.arthurdev.pokedex.viewModel.RegionViewModel

class RegionListFragment: Fragment(R.layout.fragment_region_list) {
    private lateinit var binding: FragmentRegionListBinding

    private lateinit var pokemonViewModel: RegionViewModel
    private lateinit var adapter: RegionAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegionListBinding.inflate(layoutInflater)

        pokemonViewModel = ViewModelProvider(this)[RegionViewModel::class.java]
        pokemonViewModel.pokemonLiveDate.observe(this) { regions ->
            adapter.setRegion(regions)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pokemonViewModel.loadRegion()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val recyclerView = itemView.findViewById<RecyclerView>(R.id.recycle_view)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager=layoutManager

        adapter = RegionAdapter()
        recyclerView.adapter = adapter

    }

}