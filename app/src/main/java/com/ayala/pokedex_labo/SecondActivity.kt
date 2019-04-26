package com.ayala.pokedex_labo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ayala.pokedex_labo.fragments.MainContentFragment
import com.ayala.pokedex_labo.models.Pokemon

class SecondActivity : AppCompatActivity() {


    lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        pokemon = intent?.getParcelableExtra("poke")?: Pokemon()

        val fDetalles= MainContentFragment.newInstance(pokemon)

        supportFragmentManager.beginTransaction().add(R.id.container,fDetalles).commit()
    }


}
