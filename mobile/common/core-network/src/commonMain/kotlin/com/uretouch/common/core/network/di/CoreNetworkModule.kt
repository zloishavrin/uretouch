package com.uretouch.common.core.network.di

import com.uretouch.common.core.network.api.AuthRefresherApi
import com.uretouch.common.core.network.api.DefaultAuthRefresherApi
import com.uretouch.common.core.network.provider.AuthHttpClientProvider
import com.uretouch.common.core.network.provider.HttpClientEngineProvider
import com.uretouch.common.core.network.provider.HttpClientProvider
import com.uretouch.common.core.network.provider.JsonProvider
import com.uretouch.common.core.network.refresher.BearerTokenRefresher
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

object CoreNetworkModule {
    val module = module {
        factoryOf(::HttpClientEngineProvider)
        singleOf(HttpClientEngineProvider::get)

        factoryOf(::JsonProvider)
        singleOf(JsonProvider::get)

        factoryOf(::AuthHttpClientProvider)
        single(qualifier = qualifier<AuthHttpClientQualifier>()) {
            get<AuthHttpClientProvider>().get()
        }

        factoryOf(::HttpClientProvider)
        singleOf(HttpClientProvider::get)

        singleOf(::BearerTokenRefresher)

        single<AuthRefresherApi> { DefaultAuthRefresherApi(httpClient = get(qualifier<AuthHttpClientQualifier>())) }
    }

    internal interface AuthHttpClientQualifier

    interface HttpClientQualifier
}