package com.testtask.movieapplication.data.network

import com.testtask.movieapplication.domain.models.MovieDetails
import com.testtask.movieapplication.domain.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("movies")
    suspend fun getMovies(
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movies")
    suspend fun searchMovies(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movies/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int): MovieDetails
}