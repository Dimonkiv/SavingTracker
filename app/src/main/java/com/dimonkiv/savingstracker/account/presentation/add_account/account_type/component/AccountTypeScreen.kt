package com.dimonkiv.savingstracker.account.presentation.add_account.account_type.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.core.design_system.LightGray
import com.dimonkiv.savingstracker.core.design_system.Spacing

@Composable
fun AccountTypeScreen(
    model: AccountTypeModel,
    onTypeSelect: (AccountTypeModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.L)
            .clickable {
                onTypeSelect(model)
            }
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(25.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(model.color)
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = model.title,
            color = LightGray
        )
    }
}