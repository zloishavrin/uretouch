package com.uretouch.feature.onboarding.logic.api

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.onboarding.logic.internal.DefaultOnboardingComponent

object OnboardingComponentFactory {
    fun create(
        componentContext: ComponentContext,
        dependencies: OnboardingDependencies,
        navigateNext: () -> Unit,
    ): OnboardingComponent {
        return DefaultOnboardingComponent(
            componentContext = componentContext,
            dependencies = dependencies,
            navigateNext = navigateNext,
        )
    }
}