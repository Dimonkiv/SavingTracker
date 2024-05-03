package com.dimonkiv.savingstracker.presentation.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.databinding.ItemExpenseBinding
import com.dimonkiv.savingstracker.domain.model.Expense

class ExpenseAdapter(
    private val context: Context
): RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    private val items = mutableListOf<Expense>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        with(holder.binding) {
            titleTv.text = item.title
            subtitleTv.text = item.date
            valueTv.text = "${getSign(item.isExpense)} ${item.value} $"
            iconIv.setImageDrawable(getImage(item.isExpense))
        }
    }

    private fun getSign(isExpense: Boolean): String {
        return if (isExpense) "-" else "+"
    }

    private fun getImage(isExpense: Boolean): Drawable? {
        val drawableRes = if (isExpense) R.drawable.ic_down_arrow
        else R.drawable.ic_up_arrow

        return ContextCompat.getDrawable(context, drawableRes)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<Expense>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemExpenseBinding): RecyclerView.ViewHolder(binding.root)
}