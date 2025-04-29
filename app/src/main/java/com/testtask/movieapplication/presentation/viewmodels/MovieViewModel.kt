package com.testtask.movieapplication.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.movieapplication.domain.models.Movie
import com.testtask.movieapplication.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private var currentPage = 1
    private var isLastPage = false
    var isLoading = mutableStateOf(false)
        private set

    private var currentQuery: String? = null

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collectLatest { query ->
                    searchMovies(query)
                }
        }
    }

    fun searchMovies(query: String) {
        if (query.isBlank()) return
        currentQuery = query
        currentPage = 1
        isLastPage = false
        _movies.value = emptyList()

        loadNextPage()
    }

    fun loadNextPage() {
        if (isLoading.value || isLastPage) return

        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = if (currentQuery == null) {
                    movieRepository.getMovies(currentPage)
                } else {
                    movieRepository.searchMovies(currentQuery!!, currentPage)
                }

                _movies.value = _movies.value + response.data
                currentPage++

                if (currentPage > response.metadata.page_count) {
                    isLastPage = true
                }

            } catch (e: Exception) {
                Log.e("MovieViewModel", "Error loading page", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    fun clearSearch() {
        currentQuery = null
        currentPage = 1
        isLastPage = false
        _movies.value = emptyList()
        loadNextPage()
    }
}
