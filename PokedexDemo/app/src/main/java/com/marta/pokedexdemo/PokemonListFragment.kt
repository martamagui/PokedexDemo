package com.marta.pokedexdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marta.pokedexdemo.databinding.FragmentPokemonListBinding


class PokemonListFragment : Fragment() {
    private var _binding: FragmentPokemonListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}