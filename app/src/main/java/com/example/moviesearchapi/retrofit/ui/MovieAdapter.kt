package com.example.moviesearchapi.retrofit.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesearchapi.retrofit.data.Movie
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MovieAdapter (
        private val onItemClicked: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<Movie>(MovieDiffUtilCallback()){

    init{
        delegatesManager.addDelegate(FilmAdapterDelegate(onItemClicked))
                        .addDelegate(GenreAdapterDelegate(onItemClicked))
                        .addDelegate(HeadAdapterDelegate())

    }

    class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return when {
                oldItem is Movie.Film && newItem is Movie.Film -> oldItem.id == newItem.id
                oldItem is Movie.Genre && newItem is Movie.Genre -> oldItem.genre == newItem.genre
                oldItem is Movie.Head && newItem is Movie.Head -> oldItem.head == newItem.head
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

}