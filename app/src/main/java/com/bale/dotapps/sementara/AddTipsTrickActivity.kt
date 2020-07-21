package com.bale.dotapps.sementara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bale.dotapps.R
import com.bale.dotapps.model.TipsTrickModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_tips_trick.*
import java.util.*

class AddTipsTrickActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tips_trick)

        db = Firebase.database.reference

        btn_tambah_addTipsTrick.setOnClickListener {
            addTipstrick()
        }
    }

    private fun addTipstrick(){
        val id = UUID.randomUUID().toString()
        val title = et_title_addTipsTrick.text.toString()
        val konten = et_konten_addTipsTrick.text.toString()
        val thumbnail = et_thumbnail_addTipsTrick.text.toString()

        val tipstrick = TipsTrickModel(id,title,konten,thumbnail)

        db.child("tips-trick").child(id).setValue(tipstrick)
    }
}