package com.uretouch.feature.auth.logic.root.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.feature.auth.logic.auth.internal.DefaultAuthComponent
import com.uretouch.feature.auth.logic.checking.internal.DefaultCheckingComponent
import com.uretouch.feature.auth.logic.registration.internal.DefaultRegistrationComponent
import com.uretouch.feature.auth.logic.root.api.AuthRootComponent
import com.uretouch.feature.auth.logic.root.api.AuthRootComponent.Child
import com.uretouch.feature.auth.logic.root.api.AuthRootDependencies
import com.uretouch.feature.auth.logic.root.internal.di.AuthRootModule
import kotlinx.serialization.Serializable

internal class DefaultAuthRootComponent(
    componentContext: ComponentContext,
    dependencies: AuthRootDependencies,
    private val navigateToTab: () -> Unit,
) : AuthRootComponent, ComponentContext by componentContext {

    private val scope by defaultClosableScope(modules = AuthRootModule.create(dependencies))

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.Auth) },
        childFactory = ::child,
        handleBackButton = true
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Auth -> Child.Auth(
                component = DefaultAuthComponent(
                    componentContext = componentContext,
                    authRootScope = scope,
                    navigateToRegistration = { email ->
                        navigation.pushNew(Config.Registration(email = email))
                    },
                    navigateToTab = navigateToTab,
                    navigateToCheck = { email, userId ->
                        navigation.pushNew(Config.Checking(email = email, userId = userId))
                    }
                )
            )

            is Config.Registration -> Child.Registration(
                component = DefaultRegistrationComponent(
                    componentContext = componentContext,
                    rootScope = scope,
                    email = config.email,
                    navigateToCheckEmail = { email, userId ->
                        navigation.replaceCurrent(Config.Checking(email = email, userId = userId))
                    },
                    navigateBack = navigation::pop
                )
            )

            is Config.Checking -> Child.Checking(
                component = DefaultCheckingComponent(
                    componentContext = componentContext,
                    rootScope = scope,
                    email = config.email,
                    userId = config.userId,
                    navigateToTab = navigateToTab,
                    navigateBack = navigation::pop
                )
            )
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Auth : Config

        @Serializable
        data class Registration(
            val email: String,
        ) : Config

        @Serializable
        data class Checking(
            val email: String,
            val userId: String,
        ) : Config
    }
}