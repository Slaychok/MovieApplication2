package com.testtask.movieapplication.data.repository

import com.testtask.movieapplication.data.network.MovieApiService
import com.testtask.movieapplication.domain.models.MovieDetails
import com.testtask.movieapplication.domain.models.MovieResponse
import com.testtask.movieapplication.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieRepository {

    override suspend fun getMovies(page: Int): MovieResponse {
        return movieApiService.getMovies(page)
    }

    override suspend fun searchMovies(query: String, page: Int): MovieResponse {
        return movieApiService.searchMovies(query, page)
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieApiService.getMovieDetails(movieId)
    }
}