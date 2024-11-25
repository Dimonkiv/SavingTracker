package com.dimonkiv.savingstracker.account.presentation.accounts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.account.presentation.accounts.model.TypesModel
import com.dimonkiv.savingstracker.core.design_system.Gray
import com.dimonkiv.savingstracker.core.design_system.LightDark
import com.dimonkiv.savingstracker.core.design_system.Spacing

@Composable
fun AccountGroup(
    model: TypesModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(LightDark)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.4f))
                .padding(Spacing.M),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(model.color)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = model.title,
                color = Gray
            )
        }

        Column {
            model.accounts.forEach {
                Account(it)
            }
        }
    }
}

@Composable
@Preview
fun AccountGroupPreview() {

}