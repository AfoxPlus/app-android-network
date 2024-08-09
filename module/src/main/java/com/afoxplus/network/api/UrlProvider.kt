package com.afoxplus.network.api

import javax.inject.Inject

class UrlProvider @Inject constructor(private val urlBuilder: UrlBuilder) {

    fun get(type: Type): String {
        return when (type) {
            Type.API_PRODUCTS -> urlBuilder.productService()
            Type.API_RESTAURANTS -> urlBuilder.restaurantService()
            Type.API_ORDERS -> urlBuilder.orderService()
            Type.API_INVITATION -> urlBuilder.invitationService()
            Type.API_ORDERS_V1 -> urlBuilder.orderService(1)
            Type.AWS_GATEWAY -> urlBuilder.orderService()
        }
    }

    enum class Type {
        API_PRODUCTS,
        API_RESTAURANTS,
        API_ORDERS,
        API_ORDERS_V1,
        API_INVITATION,
        AWS_GATEWAY
    }
}