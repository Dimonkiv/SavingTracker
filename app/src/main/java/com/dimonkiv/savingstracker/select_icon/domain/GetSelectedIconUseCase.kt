package com.dimonkiv.savingstracker.select_icon.domain

import com.dimonkiv.savingstracker.select_icon.domain.model.Colors
import com.dimonkiv.savingstracker.select_icon.domain.model.Icon
import com.dimonkiv.savingstracker.select_icon.domain.model.SelectedIcon
import com.dimonkiv.savingstracker.select_icon.presentation.model.ColorMap
import com.dimonkiv.savingstracker.select_icon.presentation.model.IconMap
import com.dimonkiv.savingstracker.select_icon.presentation.model.SelectedIconModel
import com.dimonkiv.savingstracker.select_icon.presentation.model.asPresentation

class GetSelectedIconUseCase {

    fun invoke(): SelectedIconModel {
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