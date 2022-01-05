package com.marta.pokedexdemo.usescases.common

import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.GONE
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marta.pokedexdemo.databinding.ItemPokemonBinding
import com.marta.pokedexdemo.model.pokemon.Pokemon
import java.util.*


class PokemonListAdapter() :
    ListAdapter<Pokemon, PokemonListAdapter.ViewHolder>(PokeItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPokemonBinding = ItemPokemonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.ViewHolder, position: Int) {
        val pokemon = getItem(position)

        holder.binding.tvPokemonId.text = "# ${pokemon.id.toString()}"
        holder.binding.tvNameCard.text =
            pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        holder.binding.tvType1.text =  pokemon.types[0].type.name
        if (pokemon.types.size>1){
            holder.binding.tvType2.text = pokemon.types[1].type.name
        }else{
            holder.binding.tvType2.visibility = View.GONE
        }


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