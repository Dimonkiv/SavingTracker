package com.dimonkiv.savingstracker.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dimonkiv.savingstracker.designsystem.AppButton
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.transaction.component.AddTransaction
import com.dimonkiv.savingstracker.transaction.component.AddTransfer
import kotlinx.coroutines.launch

@Composable
fun AddTransactionScreen() {
    val tabs = listOf("Expenses", "Income", "Transfer")
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()) {
        Column {
            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { index, title ->
                    val selected = pagerState.currentPage == index
                    Tab(
                        selected = selected,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(
                                text = title,
                                color = if (selected) AppTheme.appColorScheme.primary else AppTheme.appColorScheme.textPrimary
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
            ) { page ->
                when (page) {
                    0 -> AddTransaction()
                    1 -> AddTransaction()
                    2 -> AddTransfer()
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.XL),
                title = "Save",
                onClick = {

                }
            )
        }
    }
}