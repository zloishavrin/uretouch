package com.uretouch.feature.history.logic.root.api

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.history.logic.root.internal.DefaultHistoryRootComponent

object HistoryRootComponentFactory {
    fun create(
        componentContext: ComponentContext,
        dependencies: HistoryRootDependencies,
    ): HistoryRootComponent {
        return DefaultHistoryRootComponent(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}