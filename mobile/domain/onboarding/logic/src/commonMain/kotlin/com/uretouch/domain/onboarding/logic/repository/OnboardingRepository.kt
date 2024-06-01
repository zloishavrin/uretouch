package com.uretouch.domain.onboarding.logic.repository

interface OnboardingRepository {
    fun isNeedShowOnboarding(): Boolean
    fun setIsNeedShowOnboarding(isNeedShow: Boolean)
}