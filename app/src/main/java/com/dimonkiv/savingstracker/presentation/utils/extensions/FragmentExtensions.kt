package com.dimonkiv.savingstracker.presentation.utils.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

fun Fragment.setAppComposeContent(view: ComposeView, content: @Composable () -> Unit) {
    view.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    view.setContent(content)
}