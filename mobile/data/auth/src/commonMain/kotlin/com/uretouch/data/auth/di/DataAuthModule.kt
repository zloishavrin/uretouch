package com.uretouch.data.auth.di

import com.uretouch.common.core.preference.AuthPreference
import com.uretouch.data.auth.DefaultAuthPreference
import com.uretouch.data.auth.DefaultAuthRepository
import com.uretouch.data.auth.network.AuthApi
import com.uretouch.data.auth.network.DefaultAuthApi
import com.uretouch.domain.auth.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DataAuthModule {
    val module = module {
        singleOf(::DefaultAuthRepository) bind AuthRepository::class
        singleOf(::DefaultAuthApi) bind AuthApi::class
        singleOf(::DefaultAuthPreference) bind AuthPreference::class
    }
}