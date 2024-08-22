package com.afoxplus.network.global

interface AppProperties {
    fun isAppDebug(): Boolean
    fun getDeviceData(): String
    fun getUserUUID(): String
    fun getCurrencyID(): String
    fun getUserAuthFCMToken(): String
} 