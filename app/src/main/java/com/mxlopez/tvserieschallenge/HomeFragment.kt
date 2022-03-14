package com.mxlopez.tvserieschallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mxlopez.tvserieschallenge.adapter.MainListAdapter
import com.mxlopez.tvserieschallenge.databinding.FragmentHomeBinding
import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModel
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MainListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = context?.resources?.getString(R.string.home_label)
        val repository = TvMazeApiRepository()
        val factory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(requireActivity(), factory).get(SharedViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val add: (Show) -> Unit = { s -> sharedViewModel.addToFavorites(s) }
        val remove: (Show) -> Unit = { s -> sharedViewModel.removeFromFavorites(s) }

        sharedViewModel.shows.observe(viewLifecycleOwner) {
            adapter = MainListAdapter(it, sharedViewModel.favoriteShow.value, add, remove)
            binding.rvMainList.layoutManager = LinearLayoutManager(context)
            binding.rvMainList.adapter = adapter
        }

        binding.btnNextPage.setOnClickListener {
            sharedViewModel.nextPage()
        }

        binding.btnPrevPage.setOnClickListener {
            sharedViewModel.previousPage()
        }

        sharedViewModel.curentPage.observe(viewLifecycleOwner) {
            binding.btnPrevPage.isEnabled = sharedViewModel.curentPage.value!! > 0
            binding.tvPageNumber.text = sharedViewModel.curentPage.value!!.plus(1).toString()
        }

        return binding.root
    }

    companion object {

    }
}