package com.afoxplus.network.di

import android.content.Context
import com.afoxplus.network.api.AnnotationsHandlerInterceptor
import com.afoxplus.network.api.RetrofitGenerator
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.extensions.addUniqueInstanceInterceptor
import com.afoxplus.network.global.AppProperties
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkRetrofitModule {
    @Provides
    fun providerOrderRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        urlProvider: UrlProvider
    ): RetrofitGenerator {
        return RetrofitGenerator(
            okHttpClient = client,
            urlProvider = urlProvider,
            gsonConverterFactory = gsonConverterFactory,
        )
    }

    @Provides
    fun providerOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        urlProvider: UrlProvider,
        appProperties: AppProperties,
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addUniqueInstanceInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(ChuckerCollector(context))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
            .addUniqueInstanceInterceptor(httpLoggingInterceptor)
            .addUniqueInstanceInterceptor(AnnotationsHandlerInterceptor(urlProvider, appProperties))
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providerGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

}