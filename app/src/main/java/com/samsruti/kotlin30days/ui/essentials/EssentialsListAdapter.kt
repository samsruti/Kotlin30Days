/*
 * Copyright (c) 2020. Samsruti Dash
 * Author: Samsuti Dash
 *
 */

package com.samsruti.kotlin30days.ui.essentials

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samsruti.kotlin30days.R
import com.samsruti.kotlin30days.entities.essential.Features
import kotlinx.android.synthetic.main.item_essential.view.*

class EssentialsListAdapter(
    private val essentialResourcesData: List<Features>,
    private val callBack: (View, Int) -> Unit
) : RecyclerView.Adapter<EssentialsListAdapter.EssentialItemViewHolder>(){
    class EssentialItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Features){
            itemView.tvTitle.text = item.properties.name
            itemView.tvDescription.text = item.properties.desc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EssentialItemViewHolder {
        val container = LayoutInflater.from(parent.context).inflate(R.layout.item_essential, parent, false)
        return EssentialItemViewHolder(container)
    }

    override fun getItemCount(): Int = essentialResourcesData.size

    override fun onBindViewHolder(holder: EssentialItemViewHolder, position: Int) {
        val currentItem = essentialResourcesData[position]
        holder.bind(item = currentItem)
        holder.itemView.let {
            it.btnCall.setOnClickListener {view ->
                callBack.invoke(view, position)
            }
            it.btnMap.setOnClickListener {view ->
                callBack.invoke(view, position)
            }
            it.btnWebsite.setOnClickListener {view ->
                callBack.invoke(view, position)
            }
        }
    }
}
