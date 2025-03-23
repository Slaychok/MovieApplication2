package com.testtask.movieapplication.domain.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("data") val data: List<Movie>,
    @SerializedName("metadata") val metadata: Metadata
)