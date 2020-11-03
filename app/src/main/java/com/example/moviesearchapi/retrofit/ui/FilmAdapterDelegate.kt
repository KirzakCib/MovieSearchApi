package com.example.moviesearchapi.retrofit.ui

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesearchapi.R
import com.example.moviesearchapi.inflate
import com.example.moviesearchapi.retrofit.data.Movie
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class FilmAdapterDelegate(
private val onItemClicked: (position: Int) -> Unit
): AbsListItemAdapterDelegate<Movie.Film, Movie, FilmAdapterDelegate.Holder>() {

    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return item is Movie.Film
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(parent.inflate(R.layout.item_movie),onItemClicked)
    }

    override fun onBindViewHolder(item: Movie.Film, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
            view: View,
            private val onItemClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view){

        private val nameTextView: TextView = view.findViewById(R.id.text)
        private val imageView: ImageView = view.findViewById(R.id.icon)

        init {
            view.setOnClickListener{
                onItemClicked(adapterPosition)
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(film: Movie.Film){
            nameTextView.text = film.localized_name
            Glide.with(itemView)
                    .load(film.image_url)
                    .error(R.drawable.image)
                    .into(imageView)
        }
    }

}