package com.bale.dotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hero.*

class HeroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        setSupportActionBar(toolbar_hero)

        bottNav_hero.selectedItemId = R.id.hero_menu
        bottNav_hero.setOnNavigationItemSelectedListener(bottomNavSelectedListener())

    }

    private fun bottomNavSelectedListener()=
        BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.items_menu->{
                    startActivity(Intent(this,ItemsActivity::class.java))
                }
                R.id.tipstrick_menu->{
                    startActivity(Intent(this,TipsTrickActivity::class.java))
                }
            }
            true
        }


    override fun onStart() {
        super.onStart()
        bottNav_hero.selectedItemId = R.id.hero_menu
    }
}