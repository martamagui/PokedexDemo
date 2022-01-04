package com.marta.pokedexdemo.provider.service.api

import com.marta.pokedexdemo.model.pokemon.Pokemon
import retrofit2.Call
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon/ditto")
    fun getDitto(): Call<Pokemon>
}