package com.afoxplus.network.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.exceptions.UrlException
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class RetrofitGenerator constructor(
    private val okHttpClient: OkHttpClient,
    private val urlProvider: UrlProvider,
    private val gsonConverterFactory: GsonConverterFactory
) {

    fun <T : Any> createRetrofit(service: KClass<T>): T = createRetrofit(service.java)

    fun <T> createRetrofit(service: Class<T>): T {
        val url = service.getAnnotation(ServiceClient::class.java)?.type?.let { type ->
            urlProvider.get(type)
        } ?: throw UrlException("Missing ServiceClient annotation for class ${service.name}")

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .build()
            .create(service)
    }
}