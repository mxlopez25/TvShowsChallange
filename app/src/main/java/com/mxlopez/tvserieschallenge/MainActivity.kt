package com.mxlopez.tvserieschallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = TvMazeApiRepository()
        viewModelFactory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(this, viewModelFactory).get(SharedViewModel::class.java)

        sharedViewModel.fetchShows(0)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)
        actionBar?.title = "Home"
    }
}