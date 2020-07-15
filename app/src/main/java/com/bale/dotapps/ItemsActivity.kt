package com.bale.dotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : AppCompatActivity() {

    private lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        setSupportActionBar(toolbar_main)

        bottNav_main.selectedItemId = R.id.items_menu
        bottNav_main.setOnNavigationItemSelectedListener(bottomNavSelectedListener())
    }

    private fun bottomNavSelectedListener() =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.tipstrick_menu->{
                    startActivity(Intent(this,
                        TipsTrickActivity::class.java))
                }
                R.id.hero_menu->{
                    startActivity(Intent(this,
                        HeroActivity::class.java))
                }
            }
            true
        }
}
