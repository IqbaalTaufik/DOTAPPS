package com.bale.dotapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bale.dotapps.model.ItemModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.rv_layout_item.*

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var itemsRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        setSupportActionBar(toolbar_itemDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)

        itemsRef = Firebase.database.reference.child("items")

        getDataFromDatasbe()
    }

    private fun getDataFromDatasbe(){
        val itemID = intent.getStringExtra("ITEM_ID").toString()
        itemsRef.child(itemID).addListenerForSingleValueEvent(
            object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val itemData = snapshot.getValue(ItemModel::class.java)
                    println("itemdata => ${itemData}")
                    if(itemData != null){
                        val itemName = itemData.itemName
                        val itemCost = itemData.itemCost
                        val itemEffect = itemData.itemEffect
                        val itemIcon = itemData.itemIcon
                        val bonus = itemData.bonus
                        itemsRef.child(itemID).child("active").addListenerForSingleValueEvent(
                            object :ValueEventListener{
                                override fun onCancelled(error: DatabaseError) {
                                    TODO("Not yet implemented")
                                }
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val dataActiveMap = snapshot.value as HashMap<*,*>
                                    val activeName = dataActiveMap.get("activeName")
                                    val activeDetail = dataActiveMap.get("activeDetail")
                                    tv_itemActiveName_itemDetail.text = activeName.toString()
                                    tv_itemActiveDetail_itemDetail.text = activeDetail.toString()
                                }
                            }
                        )
                        tv_itemName_itemDetail.text = itemName
                        tv_itemCost_itemDetail.text = itemCost
                        tv_itemEffect_itemDetail.text = itemEffect
                        if(itemIcon.isNotEmpty() || itemIcon != ""){
                            Picasso.get().load(itemIcon).into(iv_itemIcon_itemDetail)
                        }
                        tv_itemBonus_itemDetail.text = bonus
                    }
                }
            }
        )
    }
}