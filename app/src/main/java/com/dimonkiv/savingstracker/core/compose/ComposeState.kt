package com.dimonkiv.savingstracker.core.compose

/**
 * Screen stage
 *
 * @property EMPTY Blank screen without content at all.
 * @property ERROR Error showing.
 * @property CONTENT Content showing.
 */
enum class Stage { EMPTY, ERROR, CONTENT }


/**
 * Represents which content should be displayed on screen: texts,
 * images, lists etc.
 *
 * @property stage Screen's stage.
 * @property loading Show/hide the loading bar.
 * @property error Error info.
 */
data class ComposeState<out Content : Any>(
    val stage: Stage = Stage.EMPTY,
    val loading: Boolean = false,
    val content: Content
)