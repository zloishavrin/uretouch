package com.uretouch.data.onboarding.di

import com.uretouch.data.onboarding.DefaultOnboardingRepository
import com.uretouch.domain.onboarding.logic.repository.OnboardingRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DataOnboardingModule {
    val module = module {
        singleOf(::DefaultOnboardingRepository) bind OnboardingRepository::class
    }
}