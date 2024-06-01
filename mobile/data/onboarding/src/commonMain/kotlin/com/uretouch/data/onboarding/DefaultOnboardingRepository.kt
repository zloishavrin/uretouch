package com.uretouch.data.onboarding

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.uretouch.domain.onboarding.logic.repository.OnboardingRepository

internal class DefaultOnboardingRepository(
    private val settings: Settings,
) : OnboardingRepository {
    override fun isNeedShowOnboarding(): Boolean {
        return settings.getBoolean(OnboardingVisibleKey, true)
    }

    override fun setIsNeedShowOnboarding(isNeedShow: Boolean) {
        settings[OnboardingVisibleKey] = isNeedShow
    }
}

private const val OnboardingVisibleKey = "OnboardingVisibleKey_v1"