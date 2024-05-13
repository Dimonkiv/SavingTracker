package com.dimonkiv.savingstracker.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.savingstracker.databinding.ItemBalanceAddBinding
import com.dimonkiv.savingstracker.databinding.ItemBalanceCardBinding
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.AccountType

class CardAdapter(
    private val listeners: CardAdapterListeners
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Account>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DEFAULT_VIEW) {
            DefaultViewHolder(
                ItemBalanceAddBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            CreatedViewHolder(
                ItemBalanceCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when(holder) {
            is DefaultViewHolder -> bindDefaultItem(holder.binding)
            is CreatedViewHolder -> bindCreatedItem(holder.binding, item)
        }
    }

    private fun bindDefaultItem(binding: ItemBalanceAddBinding) {
        binding.root.setOnClickListener {
            listeners.onAddCardClicked()
        }
    }

    private fun bindCreatedItem(binding: ItemBalanceCardBinding, item: Account) {
        with(binding) {
            titleTv.text = item.name
            balanceTv.text = item.balance.toString()
            addIv.setOnClickListener {
                listeners.onAddExpenseClicked(item.id)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]

        return when (item.type) {
            AccountType.DEFAULT -> DEFAULT_VIEW
            AccountType.CREATED -> CREATED_VIEW
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

    class DefaultViewHolder(val binding: ItemBalanceAddBinding) :
        RecyclerView.ViewHolder(binding.root)

    class CreatedViewHolder(val binding: ItemBalanceCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val DEFAULT_VIEW = 0
        private const val CREATED_VIEW = 1
    }

    interface CardAdapterListeners {
        fun onAddCardClicked()

        fun onAddExpenseClicked(id: Long)
    }
}