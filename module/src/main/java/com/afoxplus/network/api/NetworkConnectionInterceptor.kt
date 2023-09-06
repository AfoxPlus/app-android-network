package com.afoxplus.network.api

import android.content.Context
import com.afoxplus.network.exceptions.NetworkConnectionException
import com.afoxplus.network.extensions.isConnected
import okhttp3.Interceptor
import okhttp3.Response


class NetworkConnectionInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isConnected()) {
            throw NetworkConnectionException()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}