package com.bale.dotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bale.dotapps.adapter.TipsTrickAdapter
import com.bale.dotapps.model.TipsTrickModel
import com.bale.dotapps.sementara.AddTipsTrickActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_tips_trick.*

class TipsTrickActivity : AppCompatActivity(), TipsTrickAdapter.ItemClickListener {

    private lateinit var db: DatabaseReference
    private lateinit var tipstrickAdapter: TipsTrickAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_trick)

        db = Firebase.database.reference

        setSupportActionBar(toolbar_tipstrick)

        bottNav_tipstrick.selectedItemId = R.id.tipstrick_menu
        bottNav_tipstrick.setOnNavigationItemSelectedListener(bottomNavSelectedListener())

        btn_tambah_tipsTrick.setOnClickListener {
            startActivity(Intent(this,AddTipsTrickActivity::class.java))
        }

        ambilData()
    }

    private fun ambilData(){
        db.child("tips-trick").addValueEventListener(
            object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tipsArray = ArrayList<TipsTrickModel>()
                    for (tipstrickSnapshot in snapshot.children){
                        val data = tipstrickSnapshot.getValue(TipsTrickModel::class.java)
                        tipsArray.add(data!!)
                    }
                    tampilData(tipsArray)
                }
            }
        )
    }
    private fun tampilData(tipstrickList: List<TipsTrickModel>){
        tipstrickAdapter = TipsTrickAdapter()
        tipstrickAdapter.tipsTrickAdapter(tipstrickList,this)
        rv_tipstrick.layoutManager = LinearLayoutManager(this)
        rv_tipstrick.adapter = tipstrickAdapter
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

    override fun itemClickListener(tipsTrickModel: TipsTrickModel, position: Int) {
        val intent = Intent(this,TipsTrickViewActivity::class.java)
        intent.putExtra("TITLE",tipsTrickModel.title)
        intent.putExtra("KONTEN",tipsTrickModel.konten)
        intent.putExtra("THUMBNAIL",tipsTrickModel.thumbnail)
        startActivity(intent)

    }
}