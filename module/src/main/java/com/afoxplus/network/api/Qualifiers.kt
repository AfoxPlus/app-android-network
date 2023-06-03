package com.afoxplus.network.api

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductApiBaseURL
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RestaurantApiBaseURL
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OrderApiBaseURL
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductNetworkRetrofit
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RestaurantNetworkRetrofit
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OrderNetworkRetrofit
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkInterceptor
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkHttpLoggingInterceptor
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkOkHttpClient
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkGsonConverterFactory