package xyz.arthurdev.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.arthurdev.pokedex.R
import xyz.arthurdev.pokedex.adapters.RegionAdapter
import xyz.arthurdev.pokedex.databinding.FragmentRegionListBinding
import xyz.arthurdev.pokedex.viewModel.RegionViewModel

class RegionListFragment: Fragment(R.layout.fragment_region_list) {
    private lateinit var pokemonViewModel: RegionViewModel
    private lateinit var adapter: RegionAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        val binding = FragmentRegionListBinding.bind(itemView)
        binding.regionRecyclerView.layoutManager= LinearLayoutManager(context)

        adapter = RegionAdapter()
        binding.regionRecyclerView.adapter = adapter
    }

}