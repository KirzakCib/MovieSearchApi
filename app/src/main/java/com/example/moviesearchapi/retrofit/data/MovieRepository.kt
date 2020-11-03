package com.example.moviesearchapi.retrofit.data

import retrofit2.Call
import retrofit2.Response
import java.lang.RuntimeException

class MovieRepository {

    fun getMovie(
            onComplete: (List<Movie.Film>) -> Unit,
            onError: (Throwable) -> Unit
    ){
        Networking.movieApi.getValue().enqueue(
            object : retrofit2.Callback<ServerItemsWrapper<Movie.Film>> {
                override fun onResponse(
                        call: Call<ServerItemsWrapper<Movie.Film>>,
                        response: Response<ServerItemsWrapper<Movie.Film>>
                ) {
                        if(response.isSuccessful){
                           val movie =  response.body()?.films.orEmpty()
                            onComplete(movie)
                        }
                }

                override fun onFailure(call: Call<ServerItemsWrapper<Movie.Film>>, t: Throwable) {
                    onError (RuntimeException ("Error, check your network connection. Regards."))
                }

            }
        )
    }

}