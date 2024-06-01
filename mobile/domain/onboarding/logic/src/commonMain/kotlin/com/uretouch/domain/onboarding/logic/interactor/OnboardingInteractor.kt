package com.uretouch.domain.onboarding.logic.interactor

interface OnboardingInteractor {
    fun isNeedShowOnboarding(): Boolean
    fun onboardingWasShow()
}