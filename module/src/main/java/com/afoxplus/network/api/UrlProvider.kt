package com.afoxplus.network.api

import javax.inject.Inject

class UrlProvider @Inject constructor(private val urlBuilder: UrlBuilder) {

    fun get(type: Type): String {
        return when (type) {
            Type.API_PRODUCTS -> urlBuilder.productService()
            Type.API_RESTAURANTS -> urlBuilder.restaurantService()
            Type.API_ORDERS -> urlBuilder.orderService()
        }
    }

    enum class Type {
        API_PRODUCTS,
        API_RESTAURANTS,
        API_ORDERS
    }
}