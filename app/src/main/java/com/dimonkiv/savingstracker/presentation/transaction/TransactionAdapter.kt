package com.dimonkiv.savingstracker.presentation.transaction

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.savingstracker.databinding.ItemBalanceAddBinding
import com.dimonkiv.savingstracker.databinding.ItemBalanceCardBinding
import com.dimonkiv.savingstracker.databinding.ItemIncomeBinding
import com.dimonkiv.savingstracker.databinding.ItemTransferBinding
import com.dimonkiv.savingstracker.domain.model.AccountType
import com.dimonkiv.savingstracker.domain.model.TransactionType
import com.dimonkiv.savingstracker.presentation.main.CardAdapter

class TransactionAdapter(
    private val context: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<TransactionTypeState>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TRANSFER_VIEW) {
            TransferViewHolder(
                ItemTransferBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            DefaultViewHolder(
                ItemIncomeBinding.inflate(
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
            is DefaultViewHolder -> bindDefaultItem(holder.binding, item)
            is TransferViewHolder -> bindTransferItem(holder.binding, item)
        }
    }

    private fun bindDefaultItem(binding: ItemIncomeBinding, item: TransactionTypeState) {
        with(binding) {
            accountTv.text = item.expenseTitle
            balanceTv.text = item.expenseBalance
            dateTv.text = item.date

            context?.let { ctx ->
                accountTypeView.background = ContextCompat.getDrawable(ctx, item.expenseRes)
            }
        }
    }

    private fun bindTransferItem(binding: ItemTransferBinding, item: TransactionTypeState) {
        with(binding) {
            expenseAccountTv.text = item.expenseTitle
            expenseBalanceTv.text = item.expenseBalance
            incomeAccountTv.text = item.incomeTitle
            incomeBalanceTv.text = item.incomeBalance
            dateTv.text = item.date

            context?.let { ctx ->
                incomeView.background = ContextCompat.getDrawable(ctx, item.incomeRes)
                expenseView.background = ContextCompat.getDrawable(ctx, item.expenseRes)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]

        return when (item.type) {
            TransactionType.TRANSFER -> TRANSFER_VIEW
            else -> DEFAULT_VIEW
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<TransactionTypeState>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class DefaultViewHolder(val binding: ItemIncomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    class TransferViewHolder(val binding: ItemTransferBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private const val TRANSFER_VIEW = 0
        private const val DEFAULT_VIEW = 1
    }
}