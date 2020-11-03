package com.example.moviesearchapi.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesearchapi.retrofit.data.Movie
import com.example.moviesearchapi.retrofit.data.MovieRepository

class MovieListViewModel: ViewModel() {

    private val repository = MovieRepository()

    private val oneColumnListLiveData = MutableLiveData<List<Movie>>()
    private val movieListLiveData = MutableLiveData<List<Movie.Film>>()
    private val searchMovieListLiveData = MutableLiveData<List<Movie>>()
    private val updateMovieListLiveData = MutableLiveData<List<Movie.Film>>()
    private val genreListLiveData = MutableLiveData<List<Movie.Genre>>()
    private val headListLiveData = MutableLiveData<List<Movie.Head>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val toastLiveData = MutableLiveData<String>()

    val oneColumnList: LiveData<List<Movie>>
        get() = oneColumnListLiveData

    val movieList: LiveData<List<Movie.Film>>
        get() = movieListLiveData

    val searchMovieList: LiveData<List<Movie>>
        get() = searchMovieListLiveData

    val updateList: LiveData<List<Movie.Film>>
        get() = updateMovieListLiveData

    val genreList: LiveData<List<Movie.Genre>>
        get() = genreListLiveData

    val headList: LiveData<List<Movie.Head>>
        get() = headListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val toast: LiveData<String>
        get() = toastLiveData

    fun searchGenre(position: Int){
            val i = 0..movieList.value?.size!! - 1!!
        val updateFilm: MutableList<Movie.Film> = mutableListOf()
            for (i_ in i) {
                val j = 0..movieList.value?.get(i_)?.genres?.size!! - 1!!
                for (j_ in j) {
                    if (movieList.value?.get(i_)?.genres?.get(j_)!!.equals(genreListLiveData.value?.get(position - 1)?.genre?.toLowerCase())) {

                        val film: List<Movie.Film> = listOf(
                                Movie.Film(
                                        id = movieList.value?.get(i_)?.id.toString(),
                                        localized_name = movieList.value?.get(i_)?.localized_name.toString(),
                                        name = movieList.value?.get(i_)?.name.toString(),
                                        year = movieList.value?.get(i_)?.year.toString(),
                                        rating = movieList.value?.get(i_)?.rating,
                                        image_url = movieList.value?.get(i_)?.image_url,
                                        description = movieList.value?.get(i_)?.description,
                                        genres = movieList.value?.get(i_)?.genres
                                )
                        )
                        updateFilm.add(film[0])
                    }
                }
            }
                var head_genre: MutableList<Movie.Head> = mutableListOf()
                head_genre.add(Movie.Head("Жанры"))
                var head_film: MutableList<Movie.Head> = mutableListOf()
                head_film.add(Movie.Head("Фильмы"))

        oneColumnListLiveData.postValue(head_genre + genreListLiveData.value!! + head_film + updateFilm)
        updateMovieListLiveData.postValue(updateFilm)
    }

    fun genre(movie: List<Movie.Film>){
        var gen: MutableList<Movie.Genre> = mutableListOf()
        for(movie_ in movie){
            for(genre_ in movie_.genres!!){
                gen.add(Movie.Genre(genre_[0].toUpperCase() + genre_.substring(1)))
            }
        }

        var head_genre: MutableList<Movie.Head> = mutableListOf()
        head_genre.add(Movie.Head("Жанры"))

        genreListLiveData.postValue(gen.distinct().sortedBy { it.genre })
        var head_film: MutableList<Movie.Head> = mutableListOf()
        head_film.add(Movie.Head("Фильмы"))
        headListLiveData.postValue(head_genre + head_film)
        val movieList = movie.sortedBy { it.localized_name }
        oneColumnListLiveData.postValue(head_genre + gen.distinct().sortedBy { it.genre } + head_film + movieList)
        updateMovieListLiveData.postValue(movieList)
        searchMovieListLiveData.postValue(movieList)
        movieListLiveData.postValue(movieList)
    }

    fun get(){
        isLoadingLiveData.postValue(true)
        repository.getMovie(
            onComplete = { movie ->
                genre(movie)
                isLoadingLiveData.postValue(false)
            },
            onError = {throwable ->
                toastLiveData.postValue(throwable.message)
                isLoadingLiveData.postValue(false)
            }
        )
    }

}