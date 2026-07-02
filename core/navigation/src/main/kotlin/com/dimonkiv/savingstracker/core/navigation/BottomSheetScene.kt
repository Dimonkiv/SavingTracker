package com.dimonkiv.savingstracker.core.navigation

import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavMetadataKey
import androidx.navigation3.runtime.get
import androidx.navigation3.runtime.metadata
import androidx.navigation3.scene.OverlayScene
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SceneStrategyScope
import com.dimonkiv.savingstracker.core.navigation.BottomSheetSceneStrategy.Companion.bottomSheet

/**
 * Copied from the official Nav3 bottom-sheet recipe (not part of the core Navigation 3 library,
 * per the migration guide's explicit instruction to copy it into the project), extended with
 * [BottomSheetConfig] so the three sheets migrated from Nav2 keep their `skipPartiallyExpanded`/
 * custom drag-handle styling. `containerColor`/`contentColor` are intentionally left to Material3's
 * defaults: [BottomSheetConfig] is built once at Koin-module-registration time (not inside a
 * `@Composable`), so it cannot read CompositionLocal-based theme colors (e.g. `AppTheme.appColorScheme`)
 * - only plain constants. Standard `MaterialTheme` colors still apply since they propagate through
 * the composition tree regardless of module boundaries.
 */
@OptIn(ExperimentalMaterial3Api::class)
data class BottomSheetConfig(
    val properties: ModalBottomSheetProperties = ModalBottomSheetProperties(),
    val skipPartiallyExpanded: Boolean = false,
    val dragHandle: (@Composable () -> Unit)? = { BottomSheetDefaults.DragHandle() }
)

/** An [OverlayScene] that renders an [entry] within a [ModalBottomSheet]. */
@OptIn(ExperimentalMaterial3Api::class)
internal data class BottomSheetScene<T : Any>(
    override val key: T,
    override val previousEntries: List<NavEntry<T>>,
    override val overlaidEntries: List<NavEntry<T>>,
    private val entry: NavEntry<T>,
    private val config: BottomSheetConfig,
    private val onBack: () -> Unit,
) : OverlayScene<T> {

    override val entries: List<NavEntry<T>> = listOf(entry)

    override val content: @Composable (() -> Unit) = {
        val lifecycleOwner = rememberLifecycleOwner()
        val sheetState = androidx.compose.material3.rememberModalBottomSheetState(
            skipPartiallyExpanded = config.skipPartiallyExpanded
        )
        ModalBottomSheet(
            onDismissRequest = onBack,
            sheetState = sheetState,
            properties = config.properties,
            dragHandle = config.dragHandle ?: {},
        ) {
            CompositionLocalProvider(LocalLifecycleOwner provides lifecycleOwner) {
                entry.Content()
            }
        }
    }
}

/**
 * A [SceneStrategy] that displays entries that have added [bottomSheet] to their [NavEntry.metadata]
 * within a [ModalBottomSheet] instance.
 *
 * This strategy should always be added before any non-overlay scene strategies.
 */
@OptIn(ExperimentalMaterial3Api::class)
class BottomSheetSceneStrategy<T : Any> : SceneStrategy<T> {

    @Suppress("UNCHECKED_CAST")
    override fun SceneStrategyScope<T>.calculateScene(entries: List<NavEntry<T>>): Scene<T>? {
        val lastEntry = entries.lastOrNull() ?: return null
        val config = lastEntry.metadata[BottomSheetKey] ?: return null
        return BottomSheetScene(
            key = lastEntry.contentKey as T,
            previousEntries = entries.dropLast(1),
            overlaidEntries = entries.dropLast(1),
            entry = lastEntry,
            config = config,
            onBack = onBack
        )
    }

    companion object {
        /**
         * Marks a [NavEntry] as something that should be displayed within a [ModalBottomSheet].
         */
        fun bottomSheet(config: BottomSheetConfig = BottomSheetConfig()) =
            metadata {
                put(BottomSheetKey, config)
            }

        object BottomSheetKey : NavMetadataKey<BottomSheetConfig>
    }
}
