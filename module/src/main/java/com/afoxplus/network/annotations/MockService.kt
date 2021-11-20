package com.afoxplus.network.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MockService(val jsonFileName: String)
