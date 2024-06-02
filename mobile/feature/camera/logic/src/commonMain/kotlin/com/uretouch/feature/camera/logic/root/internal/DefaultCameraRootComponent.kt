package com.uretouch.feature.camera.logic.root.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.feature.camera.logic.camera.internal.DefaultCameraComponent
import com.uretouch.feature.camera.logic.photoPreview.internal.DefaultPhotoPreviewComponent
import com.uretouch.feature.camera.logic.root.api.CameraRootComponent
import com.uretouch.feature.camera.logic.root.api.CameraRootComponent.Child
import com.uretouch.feature.camera.logic.root.api.CameraRootDependencies
import com.uretouch.feature.camera.logic.root.internal.di.CameraRootModule
import kotlinx.serialization.Serializable

internal class DefaultCameraRootComponent(
    componentContext: ComponentContext,
    dependencies: CameraRootDependencies,
) : CameraRootComponent, ComponentContext by componentContext {
    private val scope by defaultClosableScope(modules = CameraRootModule.create(dependencies))

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf(Config.Camera) },
        childFactory = ::child,
        handleBackButton = true
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Camera -> Child.Camera(
                component = DefaultCameraComponent(
                    componentContext = componentContext,
                    rootScope = scope,
                    navigateToPhotoPreview = { path ->
                        navigation.pushNew(Config.PhotoPreview(path = path))
                    }
                )
            )

            is Config.PhotoPreview -> Child.PhotoPreview(
                component = DefaultPhotoPreviewComponent(
                    componentContext = componentContext,
                    rootScope = scope,
                    photoPath = config.path,
                    navigateBack = navigation::pop
                )
            )
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Camera : Config

        @Serializable
        data class PhotoPreview(
            val path: String,
        ) : Config
    }
}