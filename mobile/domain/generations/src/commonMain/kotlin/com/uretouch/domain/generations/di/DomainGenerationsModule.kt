package com.uretouch.domain.generations.di

import com.uretouch.domain.generations.interactor.DefaultGenerationsInteractor
import com.uretouch.domain.generations.interactor.GenerationsInteractor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DomainGenerationsModule {
    val module = module {
        singleOf(::DefaultGenerationsInteractor) bind GenerationsInteractor::class
    }
}