package com.uretouch.feature.settings.logic.root.api

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.settings.logic.root.internal.DefaultSettingsRootComponent

object SettingsRootComponentFactory {
    fun create(
        componentContext: ComponentContext,
        dependencies: SettingsRootDependencies,
    ): SettingsRootComponent {
        return DefaultSettingsRootComponent(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}