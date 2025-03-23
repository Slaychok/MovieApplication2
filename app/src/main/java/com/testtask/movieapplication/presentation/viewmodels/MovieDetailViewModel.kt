package com.testtask.movieapplication.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.movieapplication.domain.models.MovieDetails
import com.testtask.movieapplication.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val details = repository.getMovieDetails(movieId)
                _movieDetails.value = details
            } catch (e: Exception) {
                Log.e("MovieDetailViewModel", "Error loading movie details", e)
            }
        }
    }
}
