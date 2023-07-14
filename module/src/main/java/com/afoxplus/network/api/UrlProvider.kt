package com.afoxplus.network.api

import javax.inject.Inject

class UrlProvider @Inject constructor(private val urlBuilder: UrlBuilder) {

    fun get(type: Type): String {
        return when (type) {
            Type.API_PRODUCTS -> urlBuilder.productService()
            Type.API_RESTAURANTS -> urlBuilder.restaurantService()
            Type.API_ORDERS -> urlBuilder.orderService()
            Type.API_ORDERS_V1 -> urlBuilder.orderService(1)
        }
    }

    enum class Type {
        API_PRODUCTS,
        API_RESTAURANTS,
        API_ORDERS,
        API_ORDERS_V1,
    }
}