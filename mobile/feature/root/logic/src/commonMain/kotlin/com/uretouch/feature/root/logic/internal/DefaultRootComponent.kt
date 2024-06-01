package com.uretouch.feature.root.logic.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.logouter.LogoutUseCase
import com.uretouch.domain.onboarding.logic.interactor.OnboardingInteractor
import com.uretouch.feature.auth.logic.root.api.AuthRootComponentFactory
import com.uretouch.feature.auth.logic.root.api.AuthRootDependencies
import com.uretouch.feature.onboarding.logic.api.OnboardingComponentFactory
import com.uretouch.feature.onboarding.logic.api.OnboardingDependencies
import com.uretouch.feature.root.logic.api.RootComponent
import com.uretouch.feature.root.logic.api.RootComponent.Child
import com.uretouch.feature.root.logic.api.RootDependencies
import com.uretouch.feature.root.logic.internal.di.RootModule
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.Serializable
import ru.kontur.logistics.common.core.eventDispatcher.AuthEvent
import ru.kontur.logistics.common.core.eventDispatcher.AuthEventDispatcher

internal class DefaultRootComponent(
    componentContext: ComponentContext,
    dependencies: RootDependencies,
) : RootComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {
    private val scope by defaultClosableScope(
        modules = RootModule.create(dependencies = dependencies)
    )

    private val onboardingInteractor = scope.get<OnboardingInteractor>()

    private val authEventDispatcher = scope.get<AuthEventDispatcher>()
    private val logoutUseCase = scope.get<LogoutUseCase>()

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

    init {
        authEventDispatcher.observeEvents()
            .onEach { authEvent ->
                when (authEvent) {
                    AuthEvent.Authorize -> Unit
                    AuthEvent.Logout -> {
                        logoutUseCase.logout()
                        navigation.replaceAll(Config.Auth)
                    }
                }
            }.launchIn(coroutineScope)
    }

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Auth -> Child.AuthRoot(
                component = AuthRootComponentFactory.create(
                    componentContext = componentContext,
                    dependencies = AuthRootDependencies(
                        authInteractor = scope.get()
                    )
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
