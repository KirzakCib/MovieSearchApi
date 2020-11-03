package com.example.moviesearchapi.retrofit.ui

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesearchapi.R
import com.example.moviesearchapi.inflate
import com.example.moviesearchapi.retrofit.data.Movie
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class HeadAdapterDelegate: AbsListItemAdapterDelegate<Movie.Head, Movie, HeadAdapterDelegate.Holder>() {

    override fun isForViewType(item: Movie, items: MutableList<Movie>, position: Int): Boolean {
        return item is Movie.Head
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return HeadAdapterDelegate.Holder(parent.inflate(R.layout.item_head))
    }

    override fun onBindViewHolder(item: Movie.Head, holder: Holder, payloads: MutableList<Any>) {
        holder.bind(item)
    }

    class Holder(
            view: View
    ): RecyclerView.ViewHolder(view){

        private val headTextView: TextView = view.findViewById(R.id.text_head)

        @SuppressLint("SetTextI18n")
        fun bind(head: Movie.Head){
            headTextView.text = head.head
        }
    }

}