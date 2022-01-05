package com.marta.pokedexdemo.model.pokeList

/*
    By MartaMagui
 */
data class PokeList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)