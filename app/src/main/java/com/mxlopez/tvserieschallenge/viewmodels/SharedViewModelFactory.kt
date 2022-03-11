package com.mxlopez.tvserieschallenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository

class SharedViewModelFactory(private val repository: TvMazeApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}