package com.dimonkiv.savingstracker.presentation.select_icon

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.domain.use_cases.GetSelectedIconUseCase
import com.dimonkiv.savingstracker.presentation.core.BaseViewModel
import com.dimonkiv.savingstracker.presentation.select_icon.SelectIconContract.*
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorModel
import com.dimonkiv.savingstracker.presentation.select_icon.model.IconMap
import com.dimonkiv.savingstracker.presentation.select_icon.model.IconModel
import com.dimonkiv.savingstracker.presentation.select_icon.model.SelectedIconModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectIconViewModel @Inject constructor(
    private val useCase: GetSelectedIconUseCase
) : BaseViewModel<Event, State, Effect>() {

    init {
        setEvent(Event.LoadData)
    }

    override fun createInitialState(): State {
        return State(SelectIconState.Idle)
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.LoadData -> {
                generateIcons()
            }

            is Event.OnColorSelected -> {
                onColorSelected(event.color)
            }

            is Event.OnIconSelected -> {
                onIconSelected(event.icon)
            }

            is Event.OnSelectButtonClick -> {
                val state = (currentState.state as SelectIconState.Success).state
                setEffect(
                    Effect.OpenPreviousScreenWithArgs(
                        state.selectedIcon,
                        ColorMap.getColorName(state.selectedColor)
                    )
                )
            }
        }
    }

    private fun generateIcons() {
        setState { copy(state = SelectIconState.Loading) }
        viewModelScope.launch {
            val iconState = useCase.invoke()
            setState { copy(state = SelectIconState.Success(iconState)) }
        }
    }

    private fun onColorSelected(color: Color) {
        val state = (currentState.state as SelectIconState.Success).state
        setState {
            copy(
                state = SelectIconState.Success(
                    SelectedIconModel(
                        selectedIcon = state.selectedIcon,
                        selectedColor = color,
                        colors = updateSelectedColor(state, color),
                        icons = state.icons,
                        buttonEnabled = state.selectedIcon != -1
                    )
                )
            )
        }
    }

    private fun updateSelectedColor(state: SelectedIconModel, color: Color): List<ColorModel> {
        return state.colors
            .map {
                it.selected = it.color == color
                it
            }
            .toList()
    }

    private fun onIconSelected(icon: Int) {
        val state = (currentState.state as SelectIconState.Success).state
        setState {
            copy(
                state = SelectIconState.Success(
                    SelectedIconModel(
                        selectedIcon = icon,
                        selectedColor = state.selectedColor,
                        colors = state.colors,
                        icons = updateSelectedIcon(state, icon),
                        buttonEnabled = state.selectedIcon != -1
                    )
                )
            )
        }
    }

    private fun updateSelectedIcon(state: SelectedIconModel, icon: Int): List<IconModel> {
        return state.icons
            .map {
                it.selected = it.iconRes == icon
                it
            }
            .toList()
    }
}