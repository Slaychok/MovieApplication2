package com.testtask.movieapplication.domain.models

data class MovieDetails(
    val id: Int,
    val title: String?,
    val poster: String?,
    val year: Int?,
    val country: String?,
    val imdbRating: String?,
    val genres: List<String>?,
    val images: List<String>?,
    val plot: String?,
    val actors: String?,
    val director: String?,
    val runtime: String?
)