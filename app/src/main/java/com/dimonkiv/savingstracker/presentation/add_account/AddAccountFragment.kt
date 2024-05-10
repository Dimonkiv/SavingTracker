package com.dimonkiv.savingstracker.presentation.add_account

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.dimonkiv.savingstracker.core.BaseFragment
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

        binding.createBtn.setOnClickListener {
            viewModel.onEvent(AddAccountEvent.OnCreateClick)
        }


        collectLatestLifecycleFlow(viewModel.state) {
            binding.titleTil.isErrorEnabled = it.titleError != null
            binding.titleTil.error = it.titleError
        }

        collectLifecycleFlow(viewModel.event) {
            when(it) {
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