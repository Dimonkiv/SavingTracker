package com.dimonkiv.savingstracker.feature.select_icon.presentation.model


data class IconModel(
    val iconRes: Int,
    val selected: Boolean = false
)

fun Map<String, Int>.toIconModels(): List<IconModel> =
    keys.map { key ->
        IconModel(
            iconRes = getValue(key)
        )
    }