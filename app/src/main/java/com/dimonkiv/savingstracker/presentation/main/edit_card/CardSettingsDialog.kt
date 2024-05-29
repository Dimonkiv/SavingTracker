package com.dimonkiv.savingstracker.presentation.main.edit_card

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.databinding.DialogCardSettingsBinding
import com.dimonkiv.savingstracker.presentation.add_account.AddAccountFragment
import com.dimonkiv.savingstracker.presentation.add_expense.AddExpenseFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CardSettingsDialog: BottomSheetDialogFragment() {

    private var _binding: DialogCardSettingsBinding? = null
    private val binding: DialogCardSettingsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCardSettingsBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        val accountId = arguments?.getLong(ACCOUNT_ID_ARG) ?: 0L

        with(binding) {
            editCv.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_cardSettingsDialog_to_addAccountFragment,
                    args = AddAccountFragment.createArg(accountId)
                )
            }

            expenseCv.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_cardSettingsDialog_to_addExpenseFragment,
                    args = AddExpenseFragment.createArg(accountId)
                )
            }

            incomeCv.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_cardSettingsDialog_to_addExpenseFragment,
                    args = AddExpenseFragment.createArg(accountId)
                )
            }

            transferCv.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_cardSettingsDialog_to_addExpenseFragment,
                    args = AddExpenseFragment.createArg(accountId)
                )
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