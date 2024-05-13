package com.dimonkiv.savingstracker.presentation.add_expense

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dimonkiv.savingstracker.core.BaseFragment
import com.dimonkiv.savingstracker.databinding.FragmantAddExpenseBinding
import com.dimonkiv.savingstracker.presentation.MainActivity
import com.dimonkiv.savingstracker.presentation.add_account.AddAccountUiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddExpenseFragment :
    BaseFragment<FragmantAddExpenseBinding>(FragmantAddExpenseBinding::inflate) {

        private val viewModel: AddExpenseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
             viewModel.updateAccountId(getLong(ACCOUNT_ID_ARG))
        }
    }
    override fun initUI() {
        binding.titleEt.addTextChangedListener {
            viewModel.onEvent(AddExpenseEvent.OnTitleChange(it.toString()))
        }

        binding.expenseEt.addTextChangedListener {
            viewModel.onEvent(AddExpenseEvent.OnExpenseChange(it.toString()))
        }

        binding.expenseRb.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEvent(AddExpenseEvent.OnExpenseSelected(isChecked))
        }

        binding.incomeRb.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onEvent(AddExpenseEvent.OnIncomeSelected(isChecked))
        }

        binding.addBtn.setOnClickListener {
            viewModel.onEvent(AddExpenseEvent.OnCreateClick)
        }

        collectLatestLifecycleFlow(viewModel.state) {
            binding.titleTil.isErrorEnabled = it.titleError != null
            binding.titleTil.error = it.titleError
            binding.expenseTil.isErrorEnabled = it.expenseError != null
            binding.expenseTil.error = it.expenseError
        }

        collectLifecycleFlow(viewModel.event) {
            when(it) {
                is AddExpenseUiEvent.PopBackStack -> {
                    (activity as MainActivity).navController.popBackStack()
                }

                is AddExpenseUiEvent.ShowMessage -> {
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private const val ACCOUNT_ID_ARG = "ACCOUNT_ID"
        fun createArg(id: Long): Bundle = Bundle().apply {
            putLong(ACCOUNT_ID_ARG, id)
        }
    }
}