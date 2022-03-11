package com.mxlopez.tvserieschallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mxlopez.tvserieschallenge.adapter.MainListAdapter
import com.mxlopez.tvserieschallenge.databinding.ActivityMainBinding
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModel
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedViewModel: SharedViewModel
    lateinit var repository: TvMazeApiRepository
    lateinit var viewModelFactory: SharedViewModelFactory

    lateinit var adapter: MainListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = TvMazeApiRepository()
        viewModelFactory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(this, viewModelFactory).get(SharedViewModel::class.java)

        sharedViewModel.fetchShows(0)

        binding.rvMainList.layoutManager = LinearLayoutManager(this)

        sharedViewModel.shows.observe(this) {
            adapter = MainListAdapter(it)
            binding.rvMainList.adapter = adapter
        }

    }
}