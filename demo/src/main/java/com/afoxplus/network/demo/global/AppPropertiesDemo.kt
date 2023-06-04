package com.afoxplus.network.demo.global

import com.afoxplus.network.demo.BuildConfig
import com.afoxplus.network.global.AppProperties
import javax.inject.Inject

internal class AppPropertiesDemo @Inject constructor() : AppProperties {
    override fun isAppDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun getDeviceData(): String {
        return ""
    }
}