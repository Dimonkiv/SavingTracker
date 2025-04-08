package com.dimonkiv.savingstracker.select_icon.presentation.model

import com.dimonkiv.savingstracker.select_icon.domain.model.Icon

data class IconModel(
    val iconRes: Int,
    var selected: Boolean
)

fun Icon.asPresentation() = IconModel(
    iconRes = IconMap.icons.getOrDefault(icon, -1),
    selected = false
)

fun List<Icon>.asPresentation(): List<IconModel> {
    return map { it.asPresentation() }
}