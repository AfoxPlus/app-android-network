package com.afoxplus.network.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import com.afoxplus.network.global.AppProperties
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import java.io.InputStream


fun Context?.isConnected(): Boolean {
    return this?.let {
        val cm = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability((NetworkCapabilities.NET_CAPABILITY_INTERNET)) ?: false
    } ?: false
}

fun Context?.isAirplaneModeActive(): Boolean {
    return this?.let {
        return Settings.Global.getInt(it.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    } ?: false
}

inline fun <T : Any> Response<T>.map(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

internal fun InputStream.convertToString(): String? {
    return try {
        this.bufferedReader().use { it.readText() }
    } catch (exception: Exception) {
        null
    } finally {
        this.close()
    }
}

internal fun OkHttpClient.Builder.addUniqueInstanceInterceptor(interceptor: Interceptor): OkHttpClient.Builder {
    val iterator = this.interceptors().iterator()
    while (iterator.hasNext()) {
        if (iterator.next().javaClass == interceptor.javaClass) {
            iterator.remove()
        }
    }
    this.addInterceptor(interceptor)
    return this
}

internal fun OkHttpClient.Builder.addUniqueInstanceDebugInterceptor(
    interceptor: Interceptor,
    appProperties: AppProperties
): OkHttpClient.Builder {
    if (appProperties.isAppDebug()) {
        addUniqueInstanceInterceptor(interceptor)
    }
    return this
}