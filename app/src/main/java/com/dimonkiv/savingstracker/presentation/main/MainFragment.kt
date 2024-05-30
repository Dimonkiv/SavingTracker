package com.dimonkiv.savingstracker.presentation.main

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.presentation.base.BaseFragment
import com.dimonkiv.savingstracker.databinding.FragmentMainBinding
import com.dimonkiv.savingstracker.presentation.MainActivity
import com.dimonkiv.savingstracker.presentation.add_account.AddAccountEvent
import com.dimonkiv.savingstracker.presentation.add_account.AddAccountFragment
import com.dimonkiv.savingstracker.presentation.add_expense.AddExpenseFragment
import com.dimonkiv.savingstracker.presentation.main.edit_card.CardSettingsDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
CardAdapter.CardAdapterListeners,
CardSettingsDialog.CardSettingsDialogListeners{

    private val viewModel: MainViewModel by viewModels()

    private val cardAdapter by lazy { CardAdapter(context, this) }
    private var expenseAdapter: ExpenseAdapter? = null


    override fun initUI() {
        initViewPager()
        initExpenseAdapter()

        collectLifecycleFlow(viewModel.uiEvent) { event ->
            when(event) {
                is MainUiEvent.Navigate -> {
                    navigate(event.direction, event.arg)
                }
            }
        }

        collectLatestLifecycleFlow(viewModel.state) {
            cardAdapter.updateItems(it.accounts)
            expenseAdapter?.updateItems(it.expenses)
            binding.balanceTv.text = it.totalBalance
            showExpensesView(it.expenses.isEmpty())
        }

        viewModel.onEvent(MainEvent.FetchData)
    }

    private fun navigate(direction: Int, arg: Bundle?) {
        (activity as MainActivity).navController.navigate(
            resId = direction,
            args = arg
        )
    }

    private fun showExpensesView(isEmpty: Boolean) {
        binding.recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        binding.emptyGroup.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = cardAdapter
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
        TabLayoutMediator(binding.intoTabLayout,  binding.viewPager) { _, _ -> }.attach()

    }

    private fun initExpenseAdapter() {
        context?.let { ctx ->
            expenseAdapter = ExpenseAdapter(ctx)
            binding.recyclerView.layoutManager = LinearLayoutManager(ctx)
            binding.recyclerView.adapter = expenseAdapter
        }
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            viewModel.onPageChanged(position)
        }
    }

    override fun onAddCardClicked() {
        findNavController().navigate(R.id.action_mainFragment_to_addAccountFragment)
    }

    override fun onCardClicked(id: Long) {
        findNavController().navigate(
            resId = R.id.action_mainFragment_to_cardSettingsDialog,
            args = CardSettingsDialog.createArg(id)
        )
    }

    override fun onRemoveButtonClick(id: Long) {
        viewModel.onEvent(MainEvent.RemoveAccount(id))
    }
}