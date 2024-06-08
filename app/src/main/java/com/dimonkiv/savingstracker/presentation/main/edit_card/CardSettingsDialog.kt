package com.dimonkiv.savingstracker.presentation.main.edit_card

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.databinding.DialogCardSettingsBinding
import com.dimonkiv.savingstracker.presentation.add_account.AddAccountFragment
import com.dimonkiv.savingstracker.presentation.transaction.TransactionFragment
import com.dimonkiv.savingstracker.presentation.main.MainEvent
import com.dimonkiv.savingstracker.presentation.main.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardSettingsDialog: BottomSheetDialogFragment() {
    private var _binding: DialogCardSettingsBinding? = null
    private val binding: DialogCardSettingsBinding
        get() = _binding!!

    private var listener: CardSettingsDialogListeners? = null

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCardSettingsBinding.inflate(inflater, container, false)

        setListeners()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CardSettingsDialogListeners) {
            listener = context
        } else {
            Log.e("TEST", "Should implement DialogFragment")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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

            removeCv.setOnClickListener {
                viewModel.onEvent(MainEvent.RemoveAccount(accountId))
                dismiss()
            }

            expenseCv.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_cardSettingsDialog_to_addExpenseFragment,
                    args = TransactionFragment.createArg(accountId)
                )
            }

            incomeCv.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_cardSettingsDialog_to_addExpenseFragment,
                    args = TransactionFragment.createArg(accountId)
                )
            }

            transferCv.setOnClickListener {
                findNavController().navigate(
                    resId = R.id.action_cardSettingsDialog_to_addExpenseFragment,
                    args = TransactionFragment.createArg(accountId)
                )
            }
        }
    }

    interface CardSettingsDialogListeners {
        fun onRemoveButtonClick(id: Long)
    }

    companion object {
        private const val ACCOUNT_ID_ARG = "ACCOUNT_ID"
        fun createArg(id: Long): Bundle = Bundle().apply {
            putLong(ACCOUNT_ID_ARG, id)
        }
    }
}