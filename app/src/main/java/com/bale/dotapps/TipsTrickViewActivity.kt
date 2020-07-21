package com.bale.dotapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tips_trick_view.*
import kotlinx.android.synthetic.main.rv_layout_tipstrick.*

class TipsTrickViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_trick_view)

        setSupportActionBar(toolbar_tipstrickView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title = intent.getStringExtra("TITLE")
        val konten = intent.getStringExtra("KONTEN")
        val thumbnail = intent.getStringExtra("THUMBNAIL").toString()

        tv_title_tipstrickView.text = title
        tv_konten_tipstrickView.text = konten
        if(!thumbnail.isEmpty() || !thumbnail.isBlank()){
            Picasso.get().load(thumbnail).resize(700,500).into(iv_thumbnail_tipstrickView)
        }
    }
}