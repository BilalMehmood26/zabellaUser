package com.buzzware.zabellauser.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzzware.zabellauser.databinding.ItemSelectVehicleBinding
import com.buzzware.zabellauser.ui.model.SelectCar

class SelectVehicleAdapter(private val items: List<SelectCar>, val bookCar: () -> Unit) :
    RecyclerView.Adapter<SelectVehicleAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSelectVehicleBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectVehicleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            carsImg.setImageResource(item.imageResId)
            carNameTv.text =  item.carName
            numOfPessengersTv.text = item.passengers
            numOfLagguageTv.text = item.luggage
            costTv.text = item.fair

            bookNowBtn.setOnClickListener {
                bookCar.invoke()
            }
        }

    }
}