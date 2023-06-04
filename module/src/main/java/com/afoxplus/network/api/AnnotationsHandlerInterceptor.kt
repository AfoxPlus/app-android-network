package com.afoxplus.network.api

import com.afoxplus.network.annotations.EndpointInfo
import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.exceptions.UrlException
import com.afoxplus.network.global.AppProperties
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation

internal class AnnotationsHandlerInterceptor(
    private val urlProvider: UrlProvider,
    private val appProperties: AppProperties
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val invocation: Invocation? = request.tag(Invocation::class.java)

        invocation?.method()?.let { method ->
            val endpointInfoAnnotation = method.getAnnotation(EndpointInfo::class.java)
            val serviceClientAnnotation =
                method.declaringClass.getAnnotation(ServiceClient::class.java)
            if (endpointInfoAnnotation == null && serviceClientAnnotation == null)
                throw UrlException("Missing method annotation on: ${method.name}")
            else if (endpointInfoAnnotation != null && serviceClientAnnotation != null) {
                val serviceUrl = urlProvider.get(serviceClientAnnotation.type)
                val methodUrl = urlProvider.get(endpointInfoAnnotation.type)
                val newUrl = request.url.toString().replace(serviceUrl, methodUrl)
                val newRequest = request.newBuilder().url(newUrl)
                    .addHeader("device", appProperties.getDeviceData())
                    .build()
                return chain.proceed(newRequest)
            }
        }
        return chain.proceed(request)
    }
}