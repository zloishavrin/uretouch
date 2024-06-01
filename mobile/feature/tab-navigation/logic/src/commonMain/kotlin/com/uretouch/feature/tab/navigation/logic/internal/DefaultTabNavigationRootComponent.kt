package com.uretouch.feature.tab.navigation.logic.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.feature.camera.logic.root.api.CameraRootComponentFactory
import com.uretouch.feature.camera.logic.root.api.CameraRootDependencies
import com.uretouch.feature.history.logic.root.api.HistoryRootComponentFactory
import com.uretouch.feature.history.logic.root.api.HistoryRootDependencies
import com.uretouch.feature.settings.logic.root.api.SettingsRootComponentFactory
import com.uretouch.feature.settings.logic.root.api.SettingsRootDependencies
import com.uretouch.feature.tab.navigation.logic.api.TabNavigationRootComponent
import com.uretouch.feature.tab.navigation.logic.api.TabNavigationRootComponent.Child
import com.uretouch.feature.tab.navigation.logic.api.TabNavigationRootDependencies
import com.uretouch.feature.tab.navigation.logic.internal.di.TabNavigationRootModule
import kotlinx.serialization.Serializable

internal class DefaultTabNavigationRootComponent(
    componentContext: ComponentContext,
    dependencies: TabNavigationRootDependencies,
) : TabNavigationRootComponent, ComponentContext by componentContext {

    private val scope by defaultClosableScope(modules = TabNavigationRootModule.create(dependencies))

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.History) },
        childFactory = ::child,
        handleBackButton = true
    )

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Camera -> Child.Camera(
                component = CameraRootComponentFactory.create(
                    componentContext = componentContext,
                    dependencies = CameraRootDependencies()
                )
            )

            Config.History -> Child.History(
                component = HistoryRootComponentFactory.create(
                    componentContext = componentContext,
                    dependencies = HistoryRootDependencies()
                )
            )

            Config.Settings -> Child.Settings(
                component = SettingsRootComponentFactory.create(
                    componentContext = componentContext,
                    dependencies = SettingsRootDependencies(
                        authInteractor = scope.get()
                    )
                )
            )
        }
    }


    override val childStack: Value<ChildStack<*, Child>> = stack

    override fun onHistoryTabClick() {
        navigation.bringToFront(Config.History)
    }

    override fun onCameraTabClick() {
        navigation.bringToFront(Config.Camera)
    }

    override fun onSettingsTabClick() {
        navigation.bringToFront(Config.Settings)
    }


    @Serializable
    private sealed interface Config {

        @Serializable
        data object History : Config

        @Serializable
        data object Camera : Config

        @Serializable
        data object Settings : Config
    }
}