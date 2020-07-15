package com.bale.dotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_tips_trick.*

class TipsTrickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_trick)

        setSupportActionBar(toolbar_tipstrick)

        bottNav_tipstrick.selectedItemId = R.id.tipstrick_menu
        bottNav_tipstrick.setOnNavigationItemSelectedListener(bottomNavSelectedListener())
    }

    private fun bottomNavSelectedListener()=
        BottomNavigationView.OnNavigationItemSelectedListener{item ->
            when(item.itemId){
                R.id.items_menu->{
                    startActivity(Intent(this,
                        ItemsActivity::class.java))
                }
                R.id.hero_menu->{
                    startActivity(
                        Intent(this,
                        HeroActivity::class.java)
                    )
                }
            }
            true
        }
}