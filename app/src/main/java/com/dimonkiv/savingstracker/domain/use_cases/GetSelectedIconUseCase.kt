package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.di.Default
import com.dimonkiv.savingstracker.domain.model.Colors
import com.dimonkiv.savingstracker.domain.model.Icon
import com.dimonkiv.savingstracker.domain.model.SelectedIcon
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap
import com.dimonkiv.savingstracker.presentation.select_icon.model.IconMap
import com.dimonkiv.savingstracker.presentation.select_icon.model.SelectedIconModel
import com.dimonkiv.savingstracker.presentation.select_icon.model.asPresentation
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSelectedIconUseCase @Inject constructor(
    @Default
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun invoke(): SelectedIconModel {
        with(dispatcher) {
            val icons = IconMap.icons.keys.toList().map { Icon(it) }
            val colors = ColorMap.colors.keys.toList().map { Colors(it) }

            return SelectedIcon(
                icon = Icon(""),
                color = colors.first()
            ).asPresentation(
                icons = icons,
                colors = colors
            )
        }
    }
}