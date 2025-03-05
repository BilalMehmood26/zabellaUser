package com.buzzware.zabellauser.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buzzware.zabellauser.databinding.ItemSaveCardsBinding
import com.buzzware.zabellauser.R
import com.buzzware.zabellauser.ui.model.PaymentMethodsResponse

class CreditCardAdapter(
    val context: Context,
    val list: ArrayList<PaymentMethodsResponse.PaymentMethod>,
    val onCLick: (String,Int) -> Unit
) :
    RecyclerView.Adapter<CreditCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSaveCardsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            when (item.brand) {
                "visa" -> cardBrandIv.setImageResource(R.drawable.ic_visa)
                "master" -> cardBrandIv.setImageResource(R.drawable.ic_master)
            }

            lastFourDigits.text = item.last4
            cvvTv.text = "${item.exp_month}/${item.exp_year}"

            deleteBtn.setOnClickListener {
                onCLick.invoke(item.id,position)
            }
        }
    }

    inner class ViewHolder(val binding: ItemSaveCardsBinding) :
        RecyclerView.ViewHolder(binding.root)

}