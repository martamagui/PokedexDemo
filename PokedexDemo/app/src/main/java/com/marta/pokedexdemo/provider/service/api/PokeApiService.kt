package com.marta.pokedexdemo.provider.service.api

import com.marta.pokedexdemo.model.pokeList.PokeList
import com.marta.pokedexdemo.model.pokemon.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/*
    By MartaMagui
 */
interface PokeApiService {
    @GET("pokemon/{name}")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>
    @GET("pokemon?limit=50")
    fun get50Pokemons(): Call<PokeList>
}