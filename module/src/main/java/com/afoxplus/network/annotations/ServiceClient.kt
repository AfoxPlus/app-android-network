package com.afoxplus.network.annotations

import com.afoxplus.network.api.UrlProvider

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ServiceClient(val type: UrlProvider.Type = UrlProvider.Type.AWS_GATEWAY)