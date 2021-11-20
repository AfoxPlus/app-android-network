package com.afoxplus.network.interceptors

import android.content.Context
import com.afoxplus.network.exceptions.ApiNetworkException
import com.afoxplus.network.exceptions.NetworkException
import com.afoxplus.network.extensions.isAirplaneModeActive
import com.afoxplus.network.extensions.isConnected
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response

interface BaseInterceptor : Interceptor {
    companion object {
        val JSON_MEDIA_TYPE = "application/json".toMediaTypeOrNull()

        operator fun invoke(
            context: Context,
            block: (chain: Interceptor.Chain) -> Response
        ): Interceptor = Interceptor { chain ->
            if (!context.isConnected() || context.isAirplaneModeActive()) {
                throw NetworkException()
            }
            val response = block(chain)
            if (!response.isSuccessful)
                throw ApiNetworkException(code = response.code, message = response.message)
            response
        }
    }
}