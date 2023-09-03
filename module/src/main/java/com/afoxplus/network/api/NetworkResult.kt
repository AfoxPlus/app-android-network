package com.afoxplus.network.api

sealed interface NetworkResult<T : Any>

class NetworkSuccess<T : Any>(val data: T) : NetworkResult<T>
class NetworkError<T : Any>(val code: Int, val message: String?) : NetworkResult<T>
class NetworkException<T : Any>(val exception: Throwable) : NetworkResult<T>

suspend fun <T : Any> NetworkResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkSuccess<T>) {
        executable(data)
    }
}

suspend fun <T : Any> NetworkResult<T>.onError(
    executable: suspend (code: Int, message: String?) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkError<T>) {
        executable(code, message)
    }
}

suspend fun <T : Any> NetworkResult<T>.onException(
    executable: suspend (e: Throwable) -> Unit
): NetworkResult<T> = apply {
    if (this is NetworkException<T>) {
        executable(exception)
    }
}