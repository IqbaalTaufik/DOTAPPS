package com.bale.dotapps.sementara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bale.dotapps.R
import com.bale.dotapps.model.HeroModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_hero.*
import java.util.*

class AddHeroActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hero)

        db = Firebase.database.reference

        btn_tambah_addHero.setOnClickListener {
            setDataHero()
        }
    }

    private fun setDataHero(){
        val heroID = UUID.randomUUID().toString()
        val heroName = et_heroName_addHero.text.toString()
        val heroStats = et_heroStats_addHero.text.toString()
        val heroLore = et_heroLore_addHero.text.toString()
        val heroIcon = ""

        val heroes = HeroModel(heroID,heroName,heroStats,heroLore,heroIcon)
        db.child("heroes").child(heroID).setValue(heroes).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Berhasil tambah hero",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }
    }
}