package com.example.moviesearchapi.retrofit.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerItemsWrapper<T>(
    val films: List<T>
)