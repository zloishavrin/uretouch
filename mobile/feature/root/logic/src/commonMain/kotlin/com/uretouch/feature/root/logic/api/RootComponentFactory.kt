package com.uretouch.feature.root.logic.api

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.root.logic.internal.DefaultRootComponent

object RootComponentFactory {
    fun create(
        componentContext: ComponentContext,
        dependencies: RootDependencies,
    ): RootComponent {
        return DefaultRootComponent(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}