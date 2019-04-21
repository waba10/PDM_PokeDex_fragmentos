package com.ayala.pokedex_labo

import com.ayala.pokedex_labo.models.Pokemon


object AppConstants{
    val dataset_saveinstance_key = "CLE"
    val MAIN_LIST_KEY = "key_list_movies"
}

interface MyPokemonAdapter {
    fun changeDataSet(newDataSet : List<Pokemon>)
}