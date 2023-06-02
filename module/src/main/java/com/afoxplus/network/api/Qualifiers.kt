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