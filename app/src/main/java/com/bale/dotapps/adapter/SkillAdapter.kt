package com.bale.dotapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bale.dotapps.R
import com.bale.dotapps.model.SkillModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout_heroskill.view.*

class SkillAdapter: RecyclerView.Adapter<SkillAdapter.ViewHolder>() {

    private lateinit var skillList: List<SkillModel>
    private lateinit var clickListener: ItemClickListener

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val iv_skillIcon = view.iv_skillIcon_skillLayout
        val tv_skillName = view.tv_skillName_skillLayout

        fun bind(skillModel: SkillModel, clickListener:ItemClickListener, position: Int){
            tv_skillName.text = skillModel.skillName
            if(skillModel.skillIcon.isNotEmpty()){
                Picasso.get().load(skillModel.skillIcon).into(iv_skillIcon)
            }

            itemView.setOnClickListener {
                clickListener.itemCLickListener(skillModel,position)
            }
        }
    }

    fun skillAdapter(skillList: List<SkillModel>,clickListener: ItemClickListener){
        this.skillList = skillList
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_layout_heroskill,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return skillList.size
    }

    override fun onBindViewHolder(holder: SkillAdapter.ViewHolder, position: Int) {
        holder.bind(skillList.get(position),clickListener,position)
    }

    interface ItemClickListener{
        fun itemCLickListener(skillModel: SkillModel,position: Int)
    }
}