package com.mxlopez.tvserieschallenge.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxlopez.tvserieschallenge.models.SearchedShow
import com.mxlopez.tvserieschallenge.models.Season
import com.mxlopez.tvserieschallenge.models.Show
import com.mxlopez.tvserieschallenge.repository.TvMazeApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: TvMazeApiRepository): ViewModel() {
    private val _shows = MutableLiveData<MutableList<Show>>()
    private val _searchedShows = MutableLiveData<MutableList<SearchedShow>>()
    private val _favoriteShows = MutableLiveData<MutableList<Show>>()
    private val _currentPage = MutableLiveData<Int>(0)

    val shows: LiveData<MutableList<Show>> = _shows
    val searchedShows: LiveData<MutableList<SearchedShow>> = _searchedShows
    val favoriteShow: LiveData<MutableList<Show>> = _favoriteShows
    val curentPage: LiveData<Int> = _currentPage

    fun nextPage() {
        viewModelScope.launch {
            _currentPage.value =  _currentPage.value?.plus(1)
            fetchShows(_currentPage.value!!)
        }
    }

    fun previousPage() {
        viewModelScope.launch {
            if(_currentPage.value!! > 0) {
                _currentPage.value = _currentPage.value?.minus(1)
                fetchShows(_currentPage.value!!)
            }
        }
    }

    fun fetchShows(page: Int = 0) {
        viewModelScope.launch {
            val response = repository.getShowsPerPage(page)
            Log.d("SharedViewModel - fetchShows", response.body().toString())
            _shows.value = response.body()
        }
    }

    fun fetchFavorites() {
        viewModelScope.launch {
            if(_favoriteShows.value.isNullOrEmpty()) {
                _favoriteShows.value = mutableListOf()
            }
        }
    }

    fun searchShows(name: String = "") {
        viewModelScope.launch {
            val response = repository.getShowsByName(name)
            Log.d("SharedViewModel - searchShows", response.body().toString())
            _searchedShows.value = response.body()
        }
    }

    fun addToFavorites(show: Show) {
        viewModelScope.launch {
            if(_favoriteShows.value.isNullOrEmpty()) {
                _favoriteShows.value = mutableListOf(show)
            } else {
                _favoriteShows.value!!.add(show)
            }
        }
    }

    fun removeFromFavorites(show: Show) {
        viewModelScope.launch {
            _favoriteShows.value!!.remove(show)
        }
    }

    val addFavorite: (Show) -> Unit = { s -> viewModelScope.async { _favoriteShows.value!!.add(s) }}
    val removeFavorite: (Show) -> Unit = { s -> viewModelScope.async { _favoriteShows.value!!.remove(s) } }
}