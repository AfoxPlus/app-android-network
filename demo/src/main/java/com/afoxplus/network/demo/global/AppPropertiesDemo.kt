package com.afoxplus.network.demo.global

import com.afoxplus.network.demo.BuildConfig
import com.afoxplus.network.global.AppProperties
import java.util.UUID
import javax.inject.Inject

internal class AppPropertiesDemo @Inject constructor() : AppProperties {
    override fun isAppDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun getDeviceData(): String {
        return ""
    }

    override fun getUserUUID(): String {
        return UUID.randomUUID().toString()
    }
}