package com.dimonkiv.savingstracker.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.savingstracker.databinding.ItemBalanceCardBinding
import com.dimonkiv.savingstracker.domain.model.Account

class CardAdapter: RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val items = mutableListOf<Account>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBalanceCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding) {
            titleTv.text = item.name
            balanceTv.text = item.balance.toString()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<Account>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemBalanceCardBinding): RecyclerView.ViewHolder(binding.root)
}