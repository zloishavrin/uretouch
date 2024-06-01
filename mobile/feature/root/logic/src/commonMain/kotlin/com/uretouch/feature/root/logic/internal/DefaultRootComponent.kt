package com.uretouch.feature.root.logic.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.domain.onboarding.logic.interactor.OnboardingInteractor
import com.uretouch.feature.auth.logic.root.api.AuthRootComponentFactory
import com.uretouch.feature.auth.logic.root.api.AuthRootDependencies
import com.uretouch.feature.onboarding.logic.api.OnboardingComponentFactory
import com.uretouch.feature.onboarding.logic.api.OnboardingDependencies
import com.uretouch.feature.root.logic.api.RootComponent
import com.uretouch.feature.root.logic.api.RootComponent.Child
import com.uretouch.feature.root.logic.api.RootDependencies
import com.uretouch.feature.root.logic.internal.di.RootModule
import kotlinx.serialization.Serializable

internal class DefaultRootComponent(
    componentContext: ComponentContext,
    dependencies: RootDependencies,
) : RootComponent, ComponentContext by componentContext {
    private val scope by defaultClosableScope(
        modules = RootModule.create(dependencies = dependencies)
    )

    private val onboardingInteractor = scope.get<OnboardingInteractor>()

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { createInitialStack() },
        childFactory = ::child,
        handleBackButton = true
    )

    private fun createInitialStack(): List<Config> {
        return if (onboardingInteractor.isNeedShowOnboarding()) {
            listOf(Config.Onboarding)
        } else {
            listOf(Config.Auth)
        }
    }

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Auth -> Child.AuthRoot(
                component = AuthRootComponentFactory.create(
                    componentContext = componentContext,
                    dependencies = AuthRootDependencies()
                )
            )

            Config.Onboarding -> Child.Onboarding(
                component = OnboardingComponentFactory.create(
                    componentContext = componentContext,
                    dependencies = OnboardingDependencies(
                        onboardingInteractor = scope.get()
                    ),
                    navigateNext = {
                        navigation.replaceAll(Config.Auth)
                    }
                )
            )
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Auth : Config

        @Serializable
        data object Onboarding : Config
    }
}
