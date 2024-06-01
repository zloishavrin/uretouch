package com.uretouch.domain.onboarding.logic.di

import com.uretouch.domain.onboarding.logic.interactor.DefaultOnboardingInteractor
import com.uretouch.domain.onboarding.logic.interactor.OnboardingInteractor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object DomainOnboardingModule {
    val module = module {
        singleOf(::DefaultOnboardingInteractor) bind OnboardingInteractor::class
    }
}