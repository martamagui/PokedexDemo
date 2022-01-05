package com.marta.pokedexdemo.usescases.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marta.pokedexdemo.databinding.ItemPokemonBinding
import com.marta.pokedexdemo.model.pokemon.Pokemon


class PokemonListAdapter() :
    ListAdapter<Pokemon, PokemonListAdapter.ViewHolder>(PokeItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPokemonBinding = ItemPokemonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.binding.tvPokemonId.text = pokemon.id.toString()
        holder.binding.tvNameCard.text = pokemon.name

    }

    inner class ViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root)


}

class PokeItemCallBack : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

}