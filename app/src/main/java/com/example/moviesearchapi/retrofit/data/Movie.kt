package com.example.moviesearchapi.retrofit.data

import com.squareup.moshi.JsonClass

sealed class Movie {

    data class Head(
            val head: String
    ): Movie()

    data class Genre(
            val genre: String
    ): Movie()

    @JsonClass(generateAdapter = true)
    data class Film(
            val id: String,
            val localized_name: String,
            val name: String,
            val year: String,
            val rating: String?,
            val image_url: String?,
            val description: String?,
            val genres: List<String>?
    ): Movie()

}