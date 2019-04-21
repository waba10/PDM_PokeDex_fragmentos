package com.ayala.pokedex_labo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ayala.pokedex_labo.fragments.MainContentFragment

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val fDetalles= MainContentFragment()
        fDetalles.arguments= intent.extras
        supportFragmentManager.beginTransaction().add(R.id.container,fDetalles).commit()
    }


}
