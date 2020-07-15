package com.bale.dotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.GridLayoutManager
import com.bale.dotapps.adapter.ItemAdapter
import com.bale.dotapps.model.ItemModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : AppCompatActivity(),ItemAdapter.ItemClickListener {

    private lateinit var itemsRef: DatabaseReference
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        itemsRef = Firebase.database.reference.child("items")
        itemAdapter = ItemAdapter()

        setSupportActionBar(toolbar_main)

        bottNav_main.selectedItemId = R.id.items_menu
        bottNav_main.setOnNavigationItemSelectedListener(bottomNavSelectedListener())

        getDataDariDatabase()
    }

    private fun getDataDariDatabase(){
        itemsRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataItemList = ArrayList<ItemModel>()
                for (itemSnapshot in snapshot.children){
                    val dataItem = itemSnapshot.getValue(ItemModel::class.java)
                    dataItemList.add(dataItem!!)
                }
                tampilDataKeRecyclerView(dataItemList)
            }
        })
    }

    private fun tampilDataKeRecyclerView(dataItemList: List<ItemModel>){
        itemAdapter.itemAdapter(dataItemList,this)
        rv_item_items.apply {
            layoutManager = GridLayoutManager(this@ItemsActivity,4)
            adapter = itemAdapter
        }
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

    override fun itemClickListener(itemModel: ItemModel, position: Int) {
        TODO("Not yet implemented")
    }
}
