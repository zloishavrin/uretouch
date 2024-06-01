package com.uretouch.feature.auth.logic.root.api

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.auth.logic.root.internal.DefaultAuthRootComponent

object AuthRootComponentFactory {
    fun create(
        componentContext: ComponentContext,
        dependencies: AuthRootDependencies,
    ): AuthRootComponent {
        return DefaultAuthRootComponent(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}