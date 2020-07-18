package com.bale.dotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bale.dotapps.adapter.HeroAdapter
import com.bale.dotapps.model.HeroModel
import com.bale.dotapps.model.ItemModel
import com.bale.dotapps.sementara.AddHeroActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_hero.*

class HeroActivity : AppCompatActivity(), HeroAdapter.ItemClickListener {

    private lateinit var db: DatabaseReference
    private lateinit var itemsRef: DatabaseReference
    private lateinit var mHeroAdapter: HeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero)

        db = Firebase.database.reference
        mHeroAdapter = HeroAdapter()
        itemsRef = db.child("items")

        setSupportActionBar(toolbar_hero)

        bottNav_hero.selectedItemId = R.id.hero_menu
        bottNav_hero.setOnNavigationItemSelectedListener(bottomNavSelectedListener())


        btn_tambahHero.setOnClickListener {
            startActivity(Intent(this,AddHeroActivity::class.java))
        }

        getDataHero()
    }

    private fun getDataHero(){
        db.child("heroes").addValueEventListener(
            object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val heroList = ArrayList<HeroModel>()
                    for (heroSnapshot in snapshot.children){
                        val heroData = heroSnapshot.getValue(HeroModel::class.java)
                        heroList.add(heroData!!)
                    }
                    tampilHeroKeRecyclerView(heroList)
                }
            }
        )
    }

    private fun tampilHeroKeRecyclerView(heroList: List<HeroModel>){
        mHeroAdapter.heroAdapter(heroList,this)
        rv_hero_hero.apply {
            layoutManager = GridLayoutManager(this@HeroActivity,4)
            adapter = mHeroAdapter
        }
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

    override fun itemClickListener(heroModel: HeroModel, position: Int) {
        val intent = Intent(this,HeroDetailActivity::class.java)
        intent.putExtra("HERO_ID",heroModel.heroID)
        startActivity(intent)
    }
}