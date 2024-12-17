package com.afoxplus.network.annotations

import com.afoxplus.network.api.UrlProvider

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EndpointInfo(
    val type: UrlProvider.Type = UrlProvider.Type.AWS_BACKEND_DRIVEN_UI,
    val useFCMToken: Boolean = false
)