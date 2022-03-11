package com.mxlopez.tvserieschallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mxlopez.tvserieschallenge.databinding.FragmentHomeBinding
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModel
import com.mxlopez.tvserieschallenge.viewmodels.SharedViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val repository = TvMazeApiRepository()
        val factory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(requireActivity(), factory).get(SharedViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater)
        val view = binding.root


        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {

    }
}