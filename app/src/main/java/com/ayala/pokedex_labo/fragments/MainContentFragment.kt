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

    var pokemon = Pokemon()

    companion object {


    /*fun newInstance(index:Int):MainContentFragment{
        val f=MainContentFragment()

        val args=Bundle()
        args.putInt("INDEX",index)
        f.arguments=args

        return f
    }*/
        fun newInstance(pokemon: Pokemon): MainContentFragment{
            val newFragment = MainContentFragment()
            newFragment.pokemon = pokemon
            return newFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view=inflater.inflate(R.layout.fragment_main_content, container, false).apply {

            val aux= pokemon.id
            Glide.with(this)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$aux.png")
                .into(iv_pokemon2)
            findViewById<TextView>(R.id.frag_nombre).text = pokemon.name
            Log.d("Datos", arguments?.getInt("key_experiencia").toString())
            findViewById<TextView>(R.id.frag_experiencia).text = pokemon.experiencia.toString()
            Log.d("Datos", arguments?.getInt("key_altura").toString())
            findViewById<TextView>(R.id.frag_altura).text = pokemon.altura.toString()
            Log.d("Datos", arguments?.getInt("key_experiencia").toString())
            findViewById<TextView>(R.id.frag_peso).text = pokemon.peso.toString()
        }

        return view
    }




}
