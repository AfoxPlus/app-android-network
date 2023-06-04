package com.afoxplus.network.api

import androidx.annotation.RestrictTo
import com.afoxplus.network.global.AppProperties
import javax.inject.Inject

interface UrlBuilder {
    fun productService(version: Int? = null): String
    fun restaurantService(version: Int? = null): String
    fun orderService(version: Int? = null): String
}

@RestrictTo(RestrictTo.Scope.LIBRARY)
class URlBuilderImpl @Inject constructor(private val appProperties: AppProperties) : UrlBuilder {

    override fun productService(version: Int?): String {
        val url = if (appProperties.isAppDebug()) API_URL_PRODUCT_DEV else API_URL_PRODUCT_PROD
        return url.addVersion(version)
    }

    override fun restaurantService(version: Int?): String {
        val url =
            if (appProperties.isAppDebug()) API_URL_RESTAURANT_DEV else API_URL_RESTAURANT_PROD
        return url.addVersion(version)
    }

    override fun orderService(version: Int?): String {
        val url = if (appProperties.isAppDebug()) API_URL_ORDERS_DEV else API_URL_ORDERS_PROD
        return url.addVersion(version)
    }

    private fun String.addVersion(version: Int?): String =
        version?.let { "${this}v$version/" } ?: this
}