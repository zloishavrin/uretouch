package com.uretouch.feature.auth.logic.root.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.feature.auth.logic.auth.internal.DefaultAuthComponent
import com.uretouch.feature.auth.logic.root.api.AuthRootComponent
import com.uretouch.feature.auth.logic.root.api.AuthRootComponent.Child
import com.uretouch.feature.auth.logic.root.api.AuthRootDependencies
import com.uretouch.feature.auth.logic.root.internal.di.AuthRootModule
import kotlinx.serialization.Serializable

internal class DefaultAuthRootComponent(
    componentContext: ComponentContext,
    dependencies: AuthRootDependencies,
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
                    authRootScope = scope
                )
            )
        }
    }

    @Serializable
    private sealed interface Config {
        data object Auth : Config
    }
}