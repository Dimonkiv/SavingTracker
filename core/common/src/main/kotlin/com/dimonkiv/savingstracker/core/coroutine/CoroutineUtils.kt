package com.dimonkiv.savingstracker.core.coroutine

import kotlinx.coroutines.CancellationException

inline fun <T>runCoroutineCatching(block: () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (e: Throwable) {
        Result.failure(e)
    }
}