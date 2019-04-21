package com.ayala.pokedex_labo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayala.pokedex_labo.models.Pokemon
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.cardview_pokemon.view.*

class PokemonAdapter (var pokemones: List<Pokemon>, val clickListener: (Pokemon) -> Unit): RecyclerView.Adapter<PokemonAdapter.ViewHolder>(), MyPokemonAdapter  {
    override fun changeDataSet(newDataSet: List<Pokemon>) {
        this.pokemones = newDataSet
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount()= pokemones.size

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int){

        holder.bind(pokemones[position], clickListener)
        var aux:Int= pokemones[position].id

        Glide.with(holder.itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$aux.png")
            .into(holder.itemView.pokemon_image)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pokemon, clickListener: (Pokemon) -> Unit) = with(itemView){


            pokemon_name.text=item.name.toString()

            this.setOnClickListener { clickListener(item) }
        }
    }
}