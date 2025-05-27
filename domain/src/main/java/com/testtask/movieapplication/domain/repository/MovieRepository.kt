package com.testtask.movieapplication.domain.repository

import com.testtask.movieapplication.domain.models.MovieDetails
import com.testtask.movieapplication.domain.models.MovieResponse

interface MovieRepository {

    suspend fun getMovies(page: Int): MovieResponse
    suspend fun searchMovies(query: String, page: Int): MovieResponse
    suspend fun getMovieDetails(movieId: Int): MovieDetails
}