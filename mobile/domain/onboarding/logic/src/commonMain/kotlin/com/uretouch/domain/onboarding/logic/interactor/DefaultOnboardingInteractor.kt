package com.uretouch.domain.onboarding.logic.interactor

import com.uretouch.domain.onboarding.logic.repository.OnboardingRepository

internal class DefaultOnboardingInteractor(
    private val repository: OnboardingRepository,
) : OnboardingInteractor {
    override fun isNeedShowOnboarding(): Boolean {
        return repository.isNeedShowOnboarding()
    }

    override fun onboardingWasShow() {
        return repository.setIsNeedShowOnboarding(isNeedShow = false)
    }
}