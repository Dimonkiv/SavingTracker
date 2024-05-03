package com.dimonkiv.savingstracker.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.dimonkiv.savingstracker.core.BaseFragment
import com.dimonkiv.savingstracker.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    private val cardAdapter by lazy { CardAdapter() }
    private var expenseAdapter: ExpenseAdapter? = null


    override fun initUI() {
        initViewPager()
        initExpenseAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.accounts.collect {
                cardAdapter.updateItems(it)
                viewModel.onPageChanged(0)
            }
        }

        lifecycleScope.launch {
            viewModel.totalBalance.collect{
                binding.totalTv.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.totalBalance.collect{
                binding.totalTv.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.expenses.collect{
                expenseAdapter?.updateItems(it)
            }
        }


        viewModel.fetchData()
    }

    override fun onDestroyView() {
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = cardAdapter
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private fun initExpenseAdapter() {
        context?.let { ctx ->
            expenseAdapter = ExpenseAdapter(ctx)
            binding.expenseRecycler.layoutManager = LinearLayoutManager(ctx)
            binding.expenseRecycler.adapter = expenseAdapter
        }
    }

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            viewModel.onPageChanged(position)
        }
    }

}