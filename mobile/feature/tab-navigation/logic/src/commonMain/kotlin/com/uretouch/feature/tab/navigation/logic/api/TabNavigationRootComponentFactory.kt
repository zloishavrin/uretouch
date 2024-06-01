package com.uretouch.feature.tab.navigation.logic.api

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.tab.navigation.logic.internal.DefaultTabNavigationRootComponent

object TabNavigationRootComponentFactory {
    fun create(
        componentContext: ComponentContext,
        dependencies: TabNavigationRootDependencies,
    ): TabNavigationRootComponent {
        return DefaultTabNavigationRootComponent(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}