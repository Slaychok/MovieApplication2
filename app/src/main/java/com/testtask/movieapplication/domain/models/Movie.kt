package com.testtask.movieapplication.domain.models

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val year: String,
    val country: String,
    val imdb_rating: String,
    val genres: List<String>,
    val images: List<String>
)