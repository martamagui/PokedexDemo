package com.marta.pokedexdemo.usescases.pokemon.pokemonlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.marta.pokedexdemo.databinding.FragmentPokemonListBinding
import com.marta.pokedexdemo.model.pokeList.PokeList
import com.marta.pokedexdemo.model.pokeList.Result
import com.marta.pokedexdemo.model.pokemon.Pokemon
import com.marta.pokedexdemo.provider.service.api.PokeApi
import com.marta.pokedexdemo.usescases.common.PokemonListAdapter
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonListFragment : Fragment() {
    private var _binding: FragmentPokemonListBinding? = null
    private val binding
        get() = _binding!!
    private val pokeAdapter = PokemonListAdapter()
    private var newPokeList: MutableList<Pokemon> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            requestList()
        }
        binding.rvPokemons.adapter = pokeAdapter
        binding.rvPokemons.layoutManager = GridLayoutManager(context, 2)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //
    private fun requestList() {
        val service = PokeApi.service.get250Pokemons()
        val call = service.enqueue(object : Callback<PokeList> {
            override fun onFailure(call: Call<PokeList>, t: Throwable) {
                Log.d("OnFailure", t.message.toString())
                Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Connection faliure", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<PokeList>, response: Response<PokeList>) {
                if (response.isSuccessful) {
                    val results: List<Result> = response.body()?.results!!
                    requestPokemon(results)

                } else {
                    Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Connection faliure", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun requestPokemon(pokeList: List<Result>){
        for (item in pokeList){
            val service = PokeApi.service.getPokemon(item.name)
            val call = service.enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Log.d("OnFailure", t.message.toString())
                }
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful) {
                        val pokemonResponse : Pokemon? = response.body()
                        Log.d("Poke", pokemonResponse.toString())
//                        Log.d("Poke", response.body().toString())
                        newPokeList.add(pokemonResponse!!)
                        Log.d("Poke", newPokeList.count().toString())
                        newPokeList.sortBy { it.id }
                        pokeAdapter.submitList(newPokeList)
                        pokeAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Connection faliure", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
        }
        Log.d("Pokes", newPokeList.count().toString())
//        pokeAdapter.submitList(newList)
//        pokeAdapter.notifyDataSetChanged()
    }
}