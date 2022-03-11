package com.mxlopez.tvserieschallenge.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: TvMazeApiRepository): ViewModel() {
    private val _shows = MutableLiveData<List<Show>>()
    val shows: LiveData<List<Show>> = _shows

    fun fetchShows(page: Int = 0) {
        viewModelScope.launch {
            val response = repository.getShowsPerPage(page)
            Log.d("SharedViewModel - fetchShoes", response.body().toString())
            _shows.value = response.body()
        }
    }
}