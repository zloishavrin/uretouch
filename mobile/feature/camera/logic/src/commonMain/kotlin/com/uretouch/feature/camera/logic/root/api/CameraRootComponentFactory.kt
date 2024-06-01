package com.uretouch.feature.camera.logic.root.api

import com.arkivanov.decompose.ComponentContext
import com.uretouch.feature.camera.logic.root.internal.DefaultCameraRootComponent

object CameraRootComponentFactory {
    fun create(
        componentContext: ComponentContext,
        dependencies: CameraRootDependencies,
    ): CameraRootComponent {
        return DefaultCameraRootComponent(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}