package com.mxlopez.tvserieschallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModel
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModelFactory

class SearchFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var repository: TvMazeApiRepository
    private lateinit var sharedViewModelFactory: SharedViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_search, container, false)

        repository = TvMazeApiRepository()
        sharedViewModelFactory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel::class.java)

        return v
    }

    companion object {

    }
}