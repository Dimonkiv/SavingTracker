package com.dimonkiv.savingstracker.presentation.transaction

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.dimonkiv.savingstracker.presentation.base.BaseFragment
import com.dimonkiv.savingstracker.databinding.FragmantAddExpenseBinding
import com.dimonkiv.savingstracker.domain.model.Transaction
import com.dimonkiv.savingstracker.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment :
    BaseFragment<FragmantAddExpenseBinding>(FragmantAddExpenseBinding::inflate) {
    private val viewModel: TransactionViewModel by viewModels()

    private val adapter by lazy { TransactionAdapter(context) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            viewModel.updateAccountId(getLong(ACCOUNT_ID_ARG))
        }
    }

    override fun initUI() {
        collectLifecycleFlow(viewModel.event) {
            when (it) {
                is TransactionUiEvent.PopBackStack -> {
                    (activity as MainActivity).navController.navigateUp()
                }

                is TransactionUiEvent.ShowMessage -> {
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        collectLatestLifecycleFlow(viewModel.state) {
            showItems(it.transactions)
            initViewPager(it.transactions)
        }
        viewModel.onEvent(TransactionEvent.LoadData)
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            backBtn.setOnClickListener {
                viewModel.onEvent(TransactionEvent.OnBackClick)
            }
        }
    }

    private fun initViewPager(items: List<TransactionTypeState>) {
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.setText(items.getOrNull(pos)?.typeStr ?: "")
        }.attach()
    }

    private fun showItems(items: List<TransactionTypeState>) {
        adapter.updateItems(items)
    }


    companion object {
        private const val ACCOUNT_ID_ARG = "ACCOUNT_ID"
        fun createArg(id: Long): Bundle = Bundle().apply {
            putLong(ACCOUNT_ID_ARG, id)
        }
    }
}