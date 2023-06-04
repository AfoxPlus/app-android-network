package com.afoxplus.network.di

import com.afoxplus.network.api.URlBuilderImpl
import com.afoxplus.network.api.UrlBuilder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkUrlModule {

    @Binds
    fun bindUrlBuilder(impl: URlBuilderImpl): UrlBuilder
}