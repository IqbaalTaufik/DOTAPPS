package com.bale.dotapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bale.dotapps.R
import com.bale.dotapps.model.ItemModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout_item.view.*

class ItemAdapter:RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private lateinit var itemDataList: List<ItemModel>
    private lateinit var clickListener: ItemClickListener

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val tv_itemName = view.tv_itemName_itemLayout
        val tv_itemCost = view.tv_itemCost_itemLayout
        val iv_itemIcon = view.iv_itemIcon_itemLayout

        fun onBind(itemModel: ItemModel,clickListener: ItemClickListener,position: Int){
            tv_itemCost.text = itemModel.itemCost
            tv_itemName.text = itemModel.itemName

            Picasso.get().load(itemModel.itemIcon).into(iv_itemIcon)

            itemView.setOnClickListener {
                clickListener.itemClickListener(itemModel,position)
            }
        }
    }

    fun itemAdapter(itemDataList: List<ItemModel>, clickListener:ItemClickListener){
        this.itemDataList = itemDataList
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_layout_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return itemDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(itemDataList.get(position),clickListener,position)
    }

    interface ItemClickListener{
        fun itemClickListener(itemModel: ItemModel,position: Int)
    }
}