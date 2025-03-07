package com.buzzware.zabellauser.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SelectVehicleAdapter(private val views: List<Int>) :
    RecyclerView.Adapter<SelectVehicleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return views.size
    }

    override fun getItemViewType(position: Int): Int {
        return views[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}