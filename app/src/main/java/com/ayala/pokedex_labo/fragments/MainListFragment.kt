package com.ayala.pokedex_labo.fragments


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayala.pokedex_labo.*

import com.ayala.pokedex_labo.models.Pokemon
import kotlinx.android.synthetic.main.fragment_main_list.*
import kotlinx.android.synthetic.main.fragment_main_list.view.*
import java.lang.reflect.Array

class MainListFragment : Fragment() {

   private lateinit var  pokemones :ArrayList<Pokemon>
    private lateinit var pokemonesAdapter : MyPokemonAdapter
    var listenerTool :  SearchNewMovieListener? = null

    companion object {

        fun newInstance(dataset : ArrayList<Pokemon>): MainListFragment{
            val newFragment = MainListFragment()
            newFragment.pokemones = dataset
            return newFragment
        }
    }

    interface SearchNewMovieListener{

        fun searchMovie(pokeName: String)
        fun managePortraitItemClick(pokemon: Pokemon)
        fun manageLandscapeItemClick(pokemon: Pokemon)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_main_list, container, false)

        if(savedInstanceState != null) pokemones = savedInstanceState.getParcelableArrayList<Pokemon>(AppConstants.MAIN_LIST_KEY)?: ArrayList()


        initRecyclerView(resources.configuration.orientation, view)

        initSearchButton(view)



        return view
    }

    fun initRecyclerView(orientation:Int, container:View){
        val linearLayoutManager = LinearLayoutManager(this.context)

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            pokemonesAdapter = PokemonAdapter(pokemones, {pokemon:Pokemon->listenerTool?.managePortraitItemClick(pokemon)})
            container.movie_list_rv.adapter = pokemonesAdapter as PokemonAdapter
        }
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            pokemonesAdapter = PokemonAdapter(pokemones, { pokemon:Pokemon->listenerTool?.manageLandscapeItemClick(pokemon)})
            container.movie_list_rv.adapter = pokemonesAdapter as PokemonAdapter
        }

        container.movie_list_rv.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY, pokemones)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            pokemones = savedInstanceState.getParcelableArrayList<Pokemon>(AppConstants.MAIN_LIST_KEY)!!
        }
    }

    fun initSearchButton(container:View) = container.add_pokemon_btn.setOnClickListener {
        listenerTool?.searchMovie(pokemon_name_et.text.toString())
    }
    fun updateMoviesAdapter(pokemonList: ArrayList<Pokemon>){ pokemonesAdapter.changeDataSet(pokemonList) }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SearchNewMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de  la interfaz")
        }
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.MAIN_LIST_KEY, pokemones)
        super.onSaveInstanceState(outState)
    }*/

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }


}
