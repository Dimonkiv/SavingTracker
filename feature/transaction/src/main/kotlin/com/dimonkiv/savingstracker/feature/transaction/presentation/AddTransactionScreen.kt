package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.designsystem.AppButton
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.feature.transaction.presentation.component.AddTransaction
import com.dimonkiv.savingstracker.feature.transaction.presentation.component.AddTransfer
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionUiModel
import kotlinx.coroutines.launch

@Composable
fun AddTransactionScreen(
    state: AddTransactionUiModel,
    onSelectDateClicked: () -> Unit,
    onSelectAccountClicked: () -> Unit,
    onIntent: (AddTransactionIntent) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { state.types.size })
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()) {
        Column {
            PrimaryTabRow(selectedTabIndex = pagerState.currentPage) {
                state.types.forEachIndexed { index, type ->
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
                                text = stringResource(type.stringRes),
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
                    0, 1 -> AddTransaction(
                        state = state,
                        onSelectDateClicked = onSelectDateClicked,
                        onSelectAccountClicked = onSelectAccountClicked,
                        onIntent = onIntent
                    )
                    2 -> AddTransfer(
                        state = state,
                        onSelectDateClicked = onSelectDateClicked,
                        onSelectAccountClicked = onSelectAccountClicked,
                        onIntent = onIntent
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.XL),
                title = stringResource(R.string.save),
                onClick = {
                    onIntent(AddTransactionIntent.OnSaveClicked)
                }
            )
        }
    }
}