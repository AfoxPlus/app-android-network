package com.afoxplus.network.demo.global

import com.afoxplus.network.demo.BuildConfig
import com.afoxplus.network.global.AppProperties
import javax.inject.Inject

internal class AppPropertiesDemo @Inject constructor() : AppProperties {
    override fun isAppDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun getDeviceData(): String = "module network"

    override fun getUserUUID(): String = "6c4795bd-5a51-46e3-8cf2-3943d53ae271"

    override fun getCurrencyID(): String = "61a18be00b6de1476436de41"
}