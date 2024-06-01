package com.uretouch.feature.onboarding.logic.internal

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.onboarding.logic.api.OnboardingComponent
import com.uretouch.feature.onboarding.logic.api.OnboardingDependencies

internal class DefaultOnboardingComponent(
    componentContext: ComponentContext,
    private val dependencies: OnboardingDependencies,
    private val navigateNext: () -> Unit,
) : OnboardingComponent,
    ComponentContext by componentContext {

    override fun onBeginClick() {
        dependencies.onboardingInteractor.onboardingWasShow()
        navigateNext()
    }
}