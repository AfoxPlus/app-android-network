package com.afoxplus.network.di

import com.afoxplus.network.BuildConfig
import com.afoxplus.network.api.ENDPOINT_ORDERS_DEV
import com.afoxplus.network.api.ENDPOINT_ORDERS_PROD
import com.afoxplus.network.api.ENDPOINT_PRODUCT_DEV
import com.afoxplus.network.api.ENDPOINT_PRODUCT_PROD
import com.afoxplus.network.api.ENDPOINT_RESTAURANT_DEV
import com.afoxplus.network.api.ENDPOINT_RESTAURANT_PROD
import com.afoxplus.network.api.OrderApiBaseURL
import com.afoxplus.network.api.ProductApiBaseURL
import com.afoxplus.network.api.RestaurantApiBaseURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {
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