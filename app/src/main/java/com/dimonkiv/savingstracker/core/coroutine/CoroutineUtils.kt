package com.dimonkiv.savingstracker.core.coroutine

import kotlinx.coroutines.CancellationException

inline fun <T, R> T.runCoroutineCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (e: Throwable) {
        Result.failure(e)
    }
}