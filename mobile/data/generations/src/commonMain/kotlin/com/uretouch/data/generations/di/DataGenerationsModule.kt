package com.uretouch.data.generations.di

import com.uretouch.data.generations.DefaultGenerationsRepository
import com.uretouch.data.generations.network.DefaultGenerationsApi
import com.uretouch.data.generations.network.GenerationsApi
import com.uretouch.domain.generations.repository.GenerationsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DataGenerationsModule {
    val module = module {
        singleOf(::DefaultGenerationsRepository) bind GenerationsRepository::class
        singleOf(::DefaultGenerationsApi) bind GenerationsApi::class
    }
}