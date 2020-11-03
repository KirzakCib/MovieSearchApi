package com.example.moviesearchapi.retrofit.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearchapi.R
import com.example.moviesearchapi.inflate
import com.example.moviesearchapi.retrofit.data.Movie
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapterDelegate(
        val onItemClicked: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Movie.Genre, Movie, GenreAdapterDelegate.Holder>() {

    private lateinit var selectedItems: SparseBooleanArray

    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return item is Movie.Genre
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return GenreAdapterDelegate.Holder(parent.inflate(R.layout.item_genre), onItemClicked)
    }

    override fun onBindViewHolder(item: Movie.Genre, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
            view: View,
            private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view){

        private val genreTextView: TextView = view.findViewById(R.id.text_genre)

        init {
            view.setOnClickListener{
                onItemClicked(adapterPosition)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(genre: Movie.Genre){
            genreTextView.text = genre.genre
        }
    }

}
