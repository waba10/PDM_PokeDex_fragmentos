package com.ayala.pokedex_labo.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.ayala.pokedex_labo.R
import com.ayala.pokedex_labo.models.Pokemon
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_main_content.view.*


class MainContentFragment : Fragment() {

    /*var pokemon = Pokemon(1, "hola", "https//google.com", 12, 12 ,12 )

    companion object {
        fun newInstance(pokemon: Pokemon): MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.pokemon = pokemon
            return newFragment
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_content, container, false).apply {

            val aux=arguments?.getInt("key_id")
            Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$aux.png")
                .into(iv_pokemon2)
            findViewById<TextView>(R.id.frag_nombre).text = arguments?.getString("key_nombre").toString()
            Log.d("Datos", arguments?.getInt("key_experiencia").toString())
            findViewById<TextView>(R.id.frag_experiencia).text = arguments?.getInt("key_experiencia").toString()
            Log.d("Datos", arguments?.getInt("key_altura").toString())
            findViewById<TextView>(R.id.frag_altura).text = arguments?.getInt("key_altura").toString()
            Log.d("Datos", arguments?.getInt("key_experiencia").toString())
            findViewById<TextView>(R.id.frag_peso).text = arguments?.getInt("key_peso").toString()
        }
    }




}
