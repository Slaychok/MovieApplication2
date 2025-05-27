package com.testtask.movieapplication.domain.models

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("current_page") val current_page: String,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("page_count") val page_count: Int,
    @SerializedName("total_count") val total_count: Int
)