package com.example.moviesearchapi.retrofit.data

import retrofit2.Call
import retrofit2.http.GET

interface MovieSearchApi {

    @GET("sequeniatesttask/films.json")
    fun getValue() : Call<ServerItemsWrapper<Movie.Film>>

}