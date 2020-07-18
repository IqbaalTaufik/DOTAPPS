package com.bale.dotapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bale.dotapps.R
import com.bale.dotapps.model.HeroModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout_hero.view.*

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    private lateinit var heroList: List<HeroModel>
    private lateinit var clickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_layout_hero,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    override fun onBindViewHolder(holder: HeroAdapter.ViewHolder, position: Int) {
        holder.bind(heroList.get(position),clickListener,position)
    }

    fun heroAdapter(heroList: List<HeroModel>, clickListener: ItemClickListener){
        this.heroList = heroList
        this.clickListener = clickListener
    }

    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        val heroIcon = view.iv_heroIcon_heroLayout
        val heroName = view.tv_heroName_heroLayout

        fun bind(heroModel: HeroModel, clickListener: ItemClickListener, position: Int){
            heroName.text = heroModel.heroName
            if(heroModel.heroIcon.isNotEmpty()){
                Picasso.get().load(heroModel.heroIcon).into(heroIcon)
            }
            itemView.setOnClickListener {
                clickListener.itemClickListener(heroModel,position)
            }
        }
    }
    interface ItemClickListener{
        fun itemClickListener(heroModel: HeroModel, position: Int)
    }
}