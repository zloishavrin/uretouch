package com.uretouch.feature.history.logic.root.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.feature.history.logic.history.internal.DefaultHistoryComponent
import com.uretouch.feature.history.logic.root.api.HistoryRootComponent
import com.uretouch.feature.history.logic.root.api.HistoryRootComponent.Child
import com.uretouch.feature.history.logic.root.api.HistoryRootDependencies
import com.uretouch.feature.history.logic.root.internal.di.HistoryRootModule
import kotlinx.serialization.Serializable

internal class DefaultHistoryRootComponent(
    componentContext: ComponentContext,
    dependencies: HistoryRootDependencies,
) : HistoryRootComponent, ComponentContext by componentContext {
    private val scope by defaultClosableScope(modules = HistoryRootModule.create(dependencies))

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.History) },
        childFactory = ::child,
        handleBackButton = true
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.History -> Child.History(
                component = DefaultHistoryComponent(
                    componentContext = componentContext,
                    rootScope = scope
                )
            )
        }
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object History : Config
    }
}