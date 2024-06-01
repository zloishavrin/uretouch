package com.uretouch.feature.root.logic.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.uretouch.feature.auth.logic.root.api.AuthRootComponent
import com.uretouch.feature.onboarding.logic.api.OnboardingComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class AuthRoot(val component: AuthRootComponent) : Child()
        class Onboarding(val component: OnboardingComponent) : Child()
    }
}