package com.marta.pokedexdemo.model.pokemon

import com.google.gson.annotations.SerializedName

data class Other(
    val home: Home,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)