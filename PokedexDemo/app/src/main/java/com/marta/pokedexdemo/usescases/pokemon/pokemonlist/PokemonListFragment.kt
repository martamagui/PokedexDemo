package com.marta.pokedexdemo.usescases.pokemon.pokemonlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.marta.pokedexdemo.R
import com.marta.pokedexdemo.databinding.FragmentPokemonDetailBinding
import com.marta.pokedexdemo.databinding.FragmentPokemonListBinding
import com.marta.pokedexdemo.model.pokemon.Pokemon
import com.marta.pokedexdemo.provider.service.api.PokeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonListFragment : Fragment() {
    private var _binding: FragmentPokemonListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //
    private fun requestData() {
        val service = PokeApi.service.getDitto()
        val call = service.enqueue(object : Callback<Pokemon> {
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.d("OnFailure", t.message.toString())
                Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Connection faliure", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    Log.d("Resultado",response.body().toString())
                } else {
                    Toast.makeText(context, "(╯°□°）╯︵ ┻━┻ Connection faliure", Toast.LENGTH_SHORT).show()
                }
            }


        })
    }

}