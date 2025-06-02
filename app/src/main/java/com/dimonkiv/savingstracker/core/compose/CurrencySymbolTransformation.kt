package com.dimonkiv.savingstracker.core.compose

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CurrencySymbolTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val hasInput = text.text.isNotEmpty()
        val out = if (hasInput) "$${text.text}" else ""
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return offset + 1
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (offset == 0) 0 else (offset - 1).coerceIn(0, text.text.length)
            }
        }
        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}