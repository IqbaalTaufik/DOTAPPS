package com.bale.dotapps.sementara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bale.dotapps.R
import com.bale.dotapps.model.SkillModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_hero_skill.*
import java.util.*

class AddHeroSkillActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hero_skill)

        db = Firebase.database.reference

        btn_tambah_addSkill.setOnClickListener {
            addSkill()
        }
    }

    private fun addSkill(){
        val heroID = intent.getStringExtra("HERO_ID").toString()
        val skillID = UUID.randomUUID().toString()
        val skillName = et_skillName_addSkill.text.toString()
        val skillDesc = et_skillDesc_addSkill.text.toString()
        val skillStat = et_skillStat_addSkill.text.toString()
        val skillIcon = et_skillIcon_addSkill.text.toString()

        val skills = SkillModel(skillID,heroID,skillName,skillDesc,skillStat,skillIcon)
        db.child("skills").child(heroID).child(skillID).setValue(skills)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"Berhasil tambah skill",Toast.LENGTH_SHORT).show()
                }
            }
    }
}