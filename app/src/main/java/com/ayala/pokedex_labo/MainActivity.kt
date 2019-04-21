package com.ayala.pokedex_labo

import android.content.Intent
import android.content.res.Configuration
//import android.net.Network
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import com.ayala.pokedex_labo.fragments.MainContentFragment
import com.ayala.pokedex_labo.fragments.MainListFragment
import com.ayala.pokedex_labo.models.Pokemon
import com.ayala.pokedex_labo.AppConstants
import com.google.gson.Gson
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import com.ayala.pokedex_labo.utilities.Network

class MainActivity : AppCompatActivity(), MainListFragment.SearchNewMovieListener {
    override fun managePortraitItemClick(pokemon: Pokemon) {
        //val movieBundle = Bundle()

        var mIntent = Intent(this,  SecondActivity:: class.java)
        mIntent.putExtra("key_nombre", pokemon.name)
        mIntent.putExtra("key_peso", pokemon.peso)
        mIntent.putExtra("key_experiencia", pokemon.experiencia)
        mIntent.putExtra("key_altura", pokemon.altura)
        mIntent.putExtra("key_id", pokemon.id)
        this.startActivity(mIntent)
        //movieBundle.putParcelable("MOVIE", pokemon)
        //startActivity(Intent(this, SecondActivity::class.java).putExtra("key_nombre", pokemon.name ).putExtra("key_experiencia", pokemon.experiencia).putExtra("key_altura", pokemon.altura).putExtra("key_peso", pokemon.peso))
    }
/*
    override fun manageLandscapeItemClick(pokemon: Pokemon) {
        mainContentFragment = MainContentFragment.newInstance(pokemon)
        changeFragment(R.id.land_main_cont_fragment, mainContentFragment)
    }*/


    override fun searchMovie(pokeName: String) {
        FetchPokemonTask().execute(pokeName)
    }

    private lateinit var mainFragment : MainListFragment
    private lateinit var mainContentFragment: MainContentFragment

    private var pokemonList = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMainFragment()

        FetchPokemonTask().execute()

        //pokemonList = savedInstanceState?.getParcelableArrayList(AppConstants.dataset_saveinstance_key) as ArrayList<Pokemon>?
        //    ?: ArrayList()
    }

    fun initMainFragment(){
        mainFragment = MainListFragment.newInstance(pokemonList)

        val resource =// if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment
        /*else {
            mainContentFragment = MainContentFragment.newInstance(Pokemon())
            changeFragment(R.id.land_main_cont_fragment, mainContentFragment)

            R.id.land_main_fragment
        }*/

        changeFragment(resource, mainFragment)
    }


    fun addMovieToList(pokemon: Pokemon) {
        pokemonList.add(pokemon)
        mainFragment.updateMoviesAdapter(pokemonList)
        Log.d("Number", pokemonList.size.toString())
    }






    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }


    private inner class FetchPokemonTask : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String? {

            if (params.isNullOrEmpty()) return ""

            val pokeName = params[0]

            val pokeAPI: URL = Network().buildUrl(pokeName!!)!!


            return try {
                Network().getResponseFromHttpUrl(pokeAPI)

            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }
        }

        override fun onPostExecute(pokemonInfo: String) {
            super.onPostExecute(pokemonInfo)


            if (!pokemonInfo.isEmpty()) {
                val Jprueba = JSONObject(pokemonInfo)
                val subObj = Jprueba.getString("name")
                val subObj1 = Jprueba.getInt("base_experience")
                val subObj2 = Jprueba.getInt("height")
                val subObj3 = Jprueba.getInt("weight")
                val subObj4 = Jprueba.getInt("id")

                addMovieToList(Pokemon(subObj4, subObj, subObj1, subObj2, subObj3))
            }
            /*if (pokemonInfo != null || pokemonInfo != "") {
                Log.d("Info", pokemonInfo + "")


                    val Jprueba = JSONObject(pokemonInfo)

                    val subObj = Jprueba.getString("name")
                    val subObj1 = Jprueba.getInt("base_experience")
                    val subObj2 = Jprueba.getInt("height")
                    val subObj3 = Jprueba.getInt("weight")
                    val subObj4 = Jprueba.getInt("id")

                    addMovieToList(Pokemon(subObj4, subObj, subObj1, subObj2, subObj3 ))*/

            /*val jObj = JSONObject(pokemonInfo)
                Log.d("OBJ", jObj.getJSONArray("results")[0].toString())

                val jObjresult = jObj.getJSONArray("results")




                for(i in 0..19){
                    val jObjresultobj = JSONObject(jObjresult[i].toString())

                    addMovieToList(Pokemon(i + 1, jObjresultobj.getString("name"), jObjresultobj.getString("url")))

                }*/


       /* }*/ else {
                Log.d("error", "la cagaste en el else")
            }

        }

    }


}
