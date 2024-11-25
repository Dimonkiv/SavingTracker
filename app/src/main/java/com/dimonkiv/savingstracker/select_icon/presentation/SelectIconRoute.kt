package com.dimonkiv.savingstracker.select_icon.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun SelectIconRoute(
    navController: NavHostController,
    viewModel: SelectIconViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

  LaunchedEffect(key1 = Unit) {
      viewModel.effect.collect { event ->
          when (event) {
              is SelectIconContract.Effect.OpenPreviousScreenWithArgs -> {
                  val savedState = navController.previousBackStackEntry?.savedStateHandle
                  savedState?.set("color", event.color)
                  savedState?.set("icon", event.iconRes)

                  navController.navigateUp()
              }
          }
      }
  }

    SelectIconScreen(
        state = state,
        onBackButtonClick = {
            navController.navigateUp()
        },
        onColorSelected = {
            viewModel.setEvent(SelectIconContract.Event.OnColorSelected(it))
        },
        onIconSelected = {
            viewModel.setEvent(SelectIconContract.Event.OnIconSelected(it))
        },
        onSelectButtonClick = {
            viewModel.setEvent(SelectIconContract.Event.OnSelectButtonClick)
        }
    )



}