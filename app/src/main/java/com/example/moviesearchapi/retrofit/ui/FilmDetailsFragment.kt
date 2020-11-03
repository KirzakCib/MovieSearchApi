package com.example.moviesearchapi.retrofit.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviesearchapi.R
import com.example.moviesearchapi.retrofit.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_details_film.*

class FilmDetailsFragment: Fragment(R.layout.fragment_details_film) {

    private val args: FilmDetailsFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        click_close.setOnClickListener{
            findNavController().popBackStack()
        }
                Glide.with(image_url)
                        .load(args.imageUrl)
                        .error(R.drawable.image)
                        .into(image_url)

                text_head.text = args.localizedName
                name.text = args.name
                year.text = "Год: " + args.year
                    if(args.rating.equals( "null")) {
                        rating.text = "0"
                    }else{
                        rating.text = args.rating
                    }
                description.text = args.description

    }

}