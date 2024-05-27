package com.dimonkiv.savingstracker.presentation.add_account

import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.presentation.base.BaseFragment
import com.dimonkiv.savingstracker.databinding.FragmantAddAccountBinding
import com.dimonkiv.savingstracker.presentation.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddAccountFragment :
    BaseFragment<FragmantAddAccountBinding>(FragmantAddAccountBinding::inflate) {

    private val viewModel: AddAccountViewModel by viewModels()

    override fun initUI() {
        binding.titleEt.addTextChangedListener {
            viewModel.onEvent(AddAccountEvent.OnTitleChange(it.toString()))
        }

        binding.balanceEt.addTextChangedListener {
            viewModel.onEvent(AddAccountEvent.OnBalanceChange(it.toString()))
        }

        binding.backBtn.setOnClickListener {
            viewModel.onEvent(AddAccountEvent.OnBackButtonClick)
        }

        binding.bankBtn.setOnClickListener {
            viewModel.onEvent(AddAccountEvent.OnBankButtonClick)
        }

        binding.cashBtn.setOnClickListener {
            viewModel.onEvent(AddAccountEvent.OnCashButtonClick)
        }

        binding.investBtn.setOnClickListener {
            viewModel.onEvent(AddAccountEvent.OnInvestButtonClick)
        }

        binding.createBtn.setOnClickListener {
            viewModel.onEvent(AddAccountEvent.OnCreateClick)
        }


        collectLatestLifecycleFlow(viewModel.state) {
            binding.titleTil.isErrorEnabled = it.titleError != null
            binding.titleTil.error = it.titleError

            binding.cardView.titleTv.text = it.title
            binding.cardView.balanceTv.text = getString(R.string.balance, it.balance)
            binding.cardView.typeTv.text = getString(it.type.titleRes)
            binding.cardView.iconIv.setImageResource(it.type.iconResId)

            context?.let { ctx ->
                val backgroundColor = ContextCompat.getColor(ctx, it.type.colorRes)
                binding.cardView.root.setCardBackgroundColor(backgroundColor)

                when (it.type.type) {
                    AccountType.Type.BANK -> {
                        binding.bankBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_bank_selected)
                        binding.cashBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_cash)
                        binding.investBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_invest)
                    }

                    AccountType.Type.CASH -> {
                        binding.bankBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_bank)
                        binding.cashBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_cash_selected)
                        binding.investBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_invest)
                    }

                    AccountType.Type.INVEST -> {
                        binding.bankBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_bank)
                        binding.cashBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_cash)
                        binding.investBtn.background = ContextCompat.getDrawable(ctx, R.drawable.bg_invest_selected)
                    }

                    else -> Unit
                }
            }
        }

        collectLifecycleFlow(viewModel.event) {
            when (it) {
                is AddAccountUiEvent.PopBackStack -> {
                    (activity as MainActivity).navController.popBackStack()
                }

                is AddAccountUiEvent.ShowMessage -> {
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}