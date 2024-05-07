package com.dimonkiv.savingstracker.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.dimonkiv.savingstracker.core.BaseFragment
import com.dimonkiv.savingstracker.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    private val cardAdapter by lazy { CardAdapter() }
    private var expenseAdapter: ExpenseAdapter? = null
    private val behavior by lazy { BottomSheetBehavior.from(binding.bottomSheet) }


    override fun initUI() {
        initViewPager()
        initExpenseAdapter()
        measureHeight()
    }

    private fun measureHeight() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val screenHeight = binding.root.measuredHeight
            val mainViewHeight = binding.mainView.measuredHeight

            viewModel.onScreenHeightAvailable(screenHeight, mainViewHeight)
        }
    }

    private fun initBottomSheetView(height: Int) {
        val behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        behavior.peekHeight = height
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
            viewModel.totalBalance.collect {
                binding.balanceTv.text = it
            }
        }

        lifecycleScope.launch {
            viewModel.expenses.collect {
                expenseAdapter?.updateItems(it)
            }
        }

        lifecycleScope.launch {
            viewModel.bottomSheetHeight.collect {
                initBottomSheetView(it)
            }
        }

        lifecycleScope.launch {
            viewModel.emptyExpenses.collect {
                showExpensesView(it)
            }
        }


        viewModel.fetchData()
    }

    private fun showExpensesView(isEmpty: Boolean) {
        binding.recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        binding.emptyGroup.visibility = if (isEmpty) View.VISIBLE else View.GONE
        behavior.isDraggable = !isEmpty
    }

    override fun onDestroyView() {
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = cardAdapter
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
        TabLayoutMediator(binding.intoTabLayout,  binding.viewPager) { tab, position -> }.attach()

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

}