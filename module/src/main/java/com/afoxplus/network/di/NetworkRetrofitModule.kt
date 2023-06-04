package com.afoxplus.network.di

import com.afoxplus.network.api.AnnotationsHandlerInterceptor
import com.afoxplus.network.api.RetrofitGenerator
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.extensions.addUniqueInstanceInterceptor
import com.afoxplus.network.global.AppProperties
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        appProperties: AppProperties
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addUniqueInstanceInterceptor(AnnotationsHandlerInterceptor(urlProvider, appProperties))
            .addUniqueInstanceInterceptor(httpLoggingInterceptor)
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