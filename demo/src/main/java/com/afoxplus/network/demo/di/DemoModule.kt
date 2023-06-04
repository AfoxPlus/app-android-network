package com.afoxplus.network.demo.di

import com.afoxplus.network.demo.global.AppPropertiesDemo
import com.afoxplus.network.global.AppProperties
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DemoModule {

    @Binds
    fun binAppProperties(appPropertiesDemo: AppPropertiesDemo): AppProperties

}