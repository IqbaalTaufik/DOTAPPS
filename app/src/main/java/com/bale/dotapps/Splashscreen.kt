package com.bale.dotapps

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.activity_splashscreen.*

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        YoYo.with(Techniques.RollIn)
            .duration(1000)
            .onEnd {
                tv_namaApp_splash.visibility = View.VISIBLE
                YoYo.with(Techniques.SlideInLeft)
                    .duration(1000)
                    .onEnd {
                        YoYo.with(Techniques.RubberBand)
                            .duration(700)
                            .playOn(tv_namaApp_splash)
                    }
                    .onStart {
                        YoYo.with(Techniques.FadeIn)
                            .duration(700)
                            .playOn(tv_namaApp_splash)
                    }
                    .playOn(tv_namaApp_splash)
            }
            .playOn(iv_logo_splash)

        val handle = Handler()
        handle.postDelayed({
            val intent = Intent(this,ItemsActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}
