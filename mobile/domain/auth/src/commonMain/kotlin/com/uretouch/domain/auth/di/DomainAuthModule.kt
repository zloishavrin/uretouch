package com.uretouch.domain.auth.di

import com.uretouch.domain.auth.interactor.AuthInteractor
import com.uretouch.domain.auth.interactor.DefaultAuthInteractor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DomainAuthModule {
    val module = module {
        singleOf(::DefaultAuthInteractor) bind AuthInteractor::class
    }
}