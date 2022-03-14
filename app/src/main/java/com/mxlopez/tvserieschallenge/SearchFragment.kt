package com.mxlopez.tvserieschallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mxlopez.tvserieschallenge.adapter.MainListAdapter
import com.mxlopez.tvserieschallenge.adapter.SearchListAdapter
import com.mxlopez.tvserieschallenge.databinding.FragmentHomeBinding
import com.mxlopez.tvserieschallenge.databinding.FragmentSearchBinding
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModel
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModelFactory

class SearchFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var repository: TvMazeApiRepository
    private lateinit var sharedViewModelFactory: SharedViewModelFactory
    private lateinit var binding: FragmentSearchBinding

    private lateinit var adapter: SearchListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = context?.resources?.getString(R.string.search_label)
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        repository = TvMazeApiRepository()
        sharedViewModelFactory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel::class.java)

        binding.btnSearchShows.setOnClickListener {
            sharedViewModel.searchShows(binding.etSearch.text.toString())
        }

        sharedViewModel.searchedShows.observe(viewLifecycleOwner) {
            adapter = SearchListAdapter(
                it,
                sharedViewModel.favoriteShow.value,
                sharedViewModel.addFavorite,
                sharedViewModel.removeFavorite
            )
            binding.rvSearchList.adapter = adapter
            binding.rvSearchList.layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }

    companion object {

    }
}