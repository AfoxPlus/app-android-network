package com.afoxplus.network.api

import com.afoxplus.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @ProductApiBaseURL
    @Provides
    fun provideProductBaseUrl(): String =
        if (BuildConfig.DEBUG) ENDPOINT_PRODUCT_DEV else ENDPOINT_PRODUCT_PROD

    @RestaurantApiBaseURL
    @Provides
    fun provideRestaurantBaseUrl(): String =
        if (BuildConfig.DEBUG) ENDPOINT_RESTAURANT_DEV else ENDPOINT_RESTAURANT_PROD

    @OrderApiBaseURL
    @Provides
    fun provideOrderBaseUrl(): String =
        if (BuildConfig.DEBUG) ENDPOINT_ORDERS_DEV else ENDPOINT_ORDERS_PROD
}