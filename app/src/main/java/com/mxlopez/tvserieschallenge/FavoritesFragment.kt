package com.mxlopez.tvserieschallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mxlopez.tvserieschallenge.adapter.MainListAdapter
import com.mxlopez.tvserieschallenge.databinding.FragmentFavoritesBinding
import com.mxlopez.tvserieschallenge.databinding.FragmentSearchBinding
import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModel
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModelFactory

class FavoritesFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var repository: TvMazeApiRepository
    private lateinit var sharedViewModelFactory: SharedViewModelFactory

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        repository = TvMazeApiRepository()
        sharedViewModelFactory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel::class.java)
        sharedViewModel.fetchFavorites()

        val add: (Show) -> Unit = { s -> sharedViewModel.addToFavorites(s) }
        val remove: (Show) -> Unit = { s -> sharedViewModel.removeFromFavorites(s) }

//        adapter = MainListAdapter(sharedViewModel.favoriteShow.value, sharedViewModel.favoriteShow.value, add, remove)
//        binding.rvFavsList.adapter = adapter

        sharedViewModel.favoriteShow.observe(viewLifecycleOwner) {
            adapter = MainListAdapter(it, sharedViewModel.favoriteShow.value, add, remove)
            binding.rvFavsList.layoutManager = LinearLayoutManager(context)
            binding.rvFavsList.adapter = adapter
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    companion object {

    }
}