package com.bale.dotapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bale.dotapps.adapter.SkillAdapter
import com.bale.dotapps.model.HeroModel
import com.bale.dotapps.model.SkillModel
import com.bale.dotapps.sementara.AddHeroSkillActivity
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hero_detail.*

class HeroDetailActivity : AppCompatActivity(), SkillAdapter.ItemClickListener {

    private lateinit var db: DatabaseReference
    private lateinit var skillAdapter: SkillAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)

        setSupportActionBar(toolbar_heroDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)

        db = Firebase.database.reference
        skillAdapter = SkillAdapter()

        getHeroDetailByID()

        if(tablayout_heroDetail.selectedTabPosition == 0){
            tv_heroLore_heroDetail.visibility = View.VISIBLE
            lin_heroSkill_heroDetail.visibility = View.GONE
        }

        getDataSkill()

        tablayout_heroDetail.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0->{
                            tv_heroLore_heroDetail.visibility = View.VISIBLE
                            lin_heroSkill_heroDetail.visibility = View.GONE
                            lin_skillDetail_heroDetail.visibility = View.GONE
                        }
                        else->{
                            lin_heroSkill_heroDetail.visibility = View.VISIBLE
                            tv_heroLore_heroDetail.visibility = View.GONE
                        }
                    }
                }
            }
        )

        btn_addSkillHero_heroDetail.setOnClickListener {
            val heroID = intent.getStringExtra("HERO_ID").toString()
            val intent = Intent(this,AddHeroSkillActivity::class.java)
            intent.putExtra("HERO_ID",heroID)
            startActivity(intent)
        }
    }

    private fun getDataSkill(){
        val heroID = intent.getStringExtra("HERO_ID").toString()
        db.child("skills").child(heroID).addValueEventListener(
            object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {

                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val skillArray = ArrayList<SkillModel>()
                    for(skillSnapshot in snapshot.children){
                        if(skillSnapshot != null){
                            val skillData = skillSnapshot.getValue(SkillModel::class.java)
                            skillArray.add(skillData!!)
                        }
                    }
                    var skillArraySize = skillArray.size
                    if(skillArraySize == 0){
                        skillArraySize = 1
                    }
                    tampilSkillKeRecyView(skillArray,skillArraySize)
                }
            }
        )
    }

    private fun tampilSkillKeRecyView(skillList: List<SkillModel>,skillArraySize: Int){
        skillAdapter.skillAdapter(skillList,this)
        rv_heroSkill_heroDetail.apply {
            layoutManager = GridLayoutManager(this@HeroDetailActivity,skillArraySize)
            adapter = skillAdapter
        }
    }

    private fun getHeroDetailByID(){
        val heroID = intent.getStringExtra("HERO_ID")
        if(heroID != null){
            db.child("heroes").child(heroID).addListenerForSingleValueEvent(
                object : ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val heroData = snapshot.getValue(HeroModel::class.java)
                        if(heroData != null){
                            setUIHero(heroData)
                        }
                    }
                }
            )
        }
    }

    private fun setUIHero(heroData: HeroModel){
        val heroName = heroData.heroName
        val heroIcon = heroData.heroIcon
        val heroStat = heroData.heroStats
        val heroLore = heroData.heroLore
        tv_heroName_heroDetail.text = heroName
        tv_heroStat_heroDetail.text = heroStat
        tv_heroLore_heroDetail.text = heroLore
        if(heroIcon.isNotEmpty()){
            Picasso.get().load(heroIcon).into(iv_heroIcon_heroDetail)
        }
    }

    override fun itemCLickListener(skillModel: SkillModel, position: Int) {
        lin_skillDetail_heroDetail.visibility = View.VISIBLE
        tv_skillDesck_heroDetail.text = skillModel.skillDesc
        tv_skillStat_heroDetail.text = skillModel.skillStat
    }
}