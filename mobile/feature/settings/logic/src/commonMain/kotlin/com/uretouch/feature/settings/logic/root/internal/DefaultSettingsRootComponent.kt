package com.uretouch.feature.settings.logic.root.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.feature.settings.logic.root.api.SettingsRootComponent
import com.uretouch.feature.settings.logic.root.api.SettingsRootComponent.Child
import com.uretouch.feature.settings.logic.root.api.SettingsRootDependencies
import com.uretouch.feature.settings.logic.root.internal.di.SettingsRootModule
import com.uretouch.feature.settings.logic.settings.internal.DefaultSettingsComponent
import kotlinx.serialization.Serializable

internal class DefaultSettingsRootComponent(
    componentContext: ComponentContext,
    dependencies: SettingsRootDependencies,
) : SettingsRootComponent, ComponentContext by componentContext {
    private val scope by defaultClosableScope(modules = SettingsRootModule.create(dependencies))

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.Settings) },
        childFactory = ::child,
        handleBackButton = true
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Settings -> Child.Settings(
                component = DefaultSettingsComponent(
                    componentContext = componentContext,
                    rootScope = scope
                )
            )
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Settings : Config
    }
}