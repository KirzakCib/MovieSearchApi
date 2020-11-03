package com.example.moviesearchapi.retrofit.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesearchapi.R
import com.example.moviesearchapi.retrofit.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_details_film.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_genre.*
import kotlinx.android.synthetic.main.item_genre.text_genre
import kotlinx.android.synthetic.main.item_genre.view.*

class MovieListFragment: Fragment(R.layout.fragment_movies) {

    private var movieAdapter: MovieAdapter by autoCleared()

    private val viewModel: MovieListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList(){
        movieAdapter = MovieAdapter{
            position ->
                  if(position < viewModel.genreList.value?.size!! + viewModel.headList.value?.size!!){

                      val index = 1..viewModel.genreList.value?.size!!

                      for(index_ in index){
                          if(index_ != position)
                          recyclerview_movies.layoutManager?.findViewByPosition(index_)?.text_genre?.isSelected = false
                      }

                        recyclerview_movies.layoutManager?.findViewByPosition(position)?.text_genre?.isSelected = true

                    viewModel.searchGenre(position)
                }else{
                    val action = MovieListFragmentDirections.actionMoviesListFragmentToFilmDetailsFragment(
                        name = viewModel.updateList.value?.get(position - (viewModel.genreList.value?.size!! + viewModel.headList.value?.size!!))?.name.toString(),
                        localizedName = viewModel.updateList.value?.get(position - (viewModel.genreList.value?.size!! + viewModel.headList.value?.size!!))?.localized_name.toString(),
                        year = viewModel.updateList.value?.get(position - (viewModel.genreList.value?.size!! + viewModel.headList.value?.size!!))?.year.toString(),
                        rating = viewModel.updateList.value?.get(position - (viewModel.genreList.value?.size!! + viewModel.headList.value?.size!!))?.rating.toString(),
                        imageUrl = viewModel.updateList.value?.get(position - (viewModel.genreList.value?.size!! + viewModel.headList.value?.size!!))?.image_url.toString(),
                        description = viewModel.updateList.value?.get(position - (viewModel.genreList.value?.size!! + viewModel.headList.value?.size!!))?.description.toString()
                    )
                    findNavController().navigate(action)
               }
        }
        with(recyclerview_movies){
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(),2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
                    override fun getSpanSize(position: Int): Int {
                        return if(position < viewModel.genreList.value?.size?.plus(viewModel.headList.value?.size ?: 0) ?: 0) 2 else 1
               }
                }
            }
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel(){

        if(viewModel.movieList.value == null)
        viewModel.get()

        viewModel.isLoading.observe(viewLifecycleOwner,:: updateLoadingState)

        viewModel.oneColumnList.observe(viewLifecycleOwner){
            movieAdapter.items = it
        }

        viewModel.toast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateLoadingState(isLoading: Boolean){
        recyclerview_movies.isVisible = isLoading.not()
        progressBarMovies.isVisible = isLoading
    }

}