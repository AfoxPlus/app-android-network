package com.afoxplus.network.api

sealed interface NetworkResult<T : Any> {
    data class Success<T : Any>(val data: T) : NetworkResult<T>
    data class Error<T : Any>(val code: Int, val message: String?) : NetworkResult<T>
    data class Exception<T : Any>(val exception: Throwable) : NetworkResult<T>
}