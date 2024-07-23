package com.dimonkiv.savingstracker.presentation.utils.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <R> ViewModel.executeUseCase(block: () -> R): Result<R> =
    viewModelScope.executeUseCase(block)

suspend inline fun <R> CoroutineScope.executeUseCase(block: () -> R): Result<R> {
    return runSuspendCatching(block)
        .onFailure { e ->
            coroutineScope { coroutineContext }.let { coroutineContext ->
                coroutineContext[CoroutineExceptionHandler]?.run {
                    handleException(coroutineContext, e)
                }
            }
        }
}

inline fun <R> runSuspendCatching(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (cancellationException: CancellationException) {
        throw cancellationException
    } catch (e: Throwable) {
        Result.failure(e)
    }
}