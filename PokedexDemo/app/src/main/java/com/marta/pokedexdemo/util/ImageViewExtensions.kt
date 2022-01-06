package com.marta.pokedexdemo.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.marta.pokedexdemo.R

fun ImageView.imageLink(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable._01)
        .into(this)
}