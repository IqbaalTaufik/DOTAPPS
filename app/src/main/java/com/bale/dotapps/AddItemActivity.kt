package com.bale.dotapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_item.*
import java.util.*
import kotlin.collections.HashMap

class AddItemActivity : AppCompatActivity() {

    private lateinit var itemsRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        itemsRef = Firebase.database.reference.child("items")

        btn_addItem.setOnClickListener {
            setDataToDatabase()
        }

    }

    private fun setDataToDatabase(){
        //edittext
        val bonus = et_itemBonus.text.toString()
        val itemCost = et_itemCost.text.toString()
        val itemEffect = et_itemEffect.toString()
        val itemName = et_itemName.text.toString()
        val itemActiveName = et_itemActiveName.text.toString()
        val itemActiveDetail = et_itemActiveDetail.text.toString()

        val itemID = UUID.randomUUID().toString()
        val itemsMap = HashMap<String,Any>()
        itemsMap.put("bonus",bonus)
        itemsMap.put("itemCost",itemCost)
        itemsMap.put("itemEffect",itemEffect)
        itemsMap.put("itemName",itemName)
        itemsMap.put("itemID",itemID)
        itemsMap.put("itemIcon","")

        val itemsActiveMap = HashMap<String,Any>()
        itemsActiveMap.put("activeDetail",itemActiveDetail)
        itemsActiveMap.put("activeName",itemActiveName)

        itemsRef.child(itemID).setValue(itemsMap).addOnCompleteListener {
            if(it.isSuccessful){
                itemsRef.child(itemID).child("active").setValue(itemsActiveMap)
            }
        }
    }

}