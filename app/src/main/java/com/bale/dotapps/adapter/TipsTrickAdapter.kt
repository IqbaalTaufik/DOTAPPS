package com.bale.dotapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bale.dotapps.R
import com.bale.dotapps.model.TipsTrickModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout_tipstrick.view.*

class TipsTrickAdapter: RecyclerView.Adapter<TipsTrickAdapter.ViewHolder>() {

    private lateinit var tipsTrickList: List<TipsTrickModel>
    private lateinit var clickListener: ItemClickListener

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val tvTitle = view.tv_title_tipstrickLayout
        val tvKonten = view.tv_konten_tipstrickLayout
        val ivImage = view.thumbnail_tipstrickLayout

        fun bind(tipsTrickModel: TipsTrickModel, clickListener: ItemClickListener, position: Int){
            tvTitle.text = tipsTrickModel.title
            tvKonten.text = tipsTrickModel.konten
            if(!tipsTrickModel.thumbnail.isEmpty() || !tipsTrickModel.thumbnail.isBlank()){
                Picasso.get().load(tipsTrickModel.thumbnail).resize(500,500).into(ivImage)
            }

            itemView.setOnClickListener {
                clickListener.itemClickListener(tipsTrickModel,position)
            }
        }
    }
    interface ItemClickListener{
        fun itemClickListener(tipsTrickModel: TipsTrickModel,position: Int)
    }

    fun tipsTrickAdapter(tipsTrickList: List<TipsTrickModel>, clickListener: ItemClickListener){
        this.tipsTrickList = tipsTrickList
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_layout_tipstrick,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return tipsTrickList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tipsTrickList.get(position),clickListener,position)
    }
}