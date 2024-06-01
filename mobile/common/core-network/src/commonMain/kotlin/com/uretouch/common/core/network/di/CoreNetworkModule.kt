package com.uretouch.common.core.network.di

import com.uretouch.common.core.network.provider.HttpClientEngineProvider
import com.uretouch.common.core.network.provider.JsonProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object CoreNetworkModule {
    val module = module {
        factoryOf(::HttpClientEngineProvider)
        singleOf(HttpClientEngineProvider::get)

        factoryOf(::JsonProvider)
        singleOf(JsonProvider::get)
    }
}