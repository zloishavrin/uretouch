package com.uretouch.feature.camera.logic.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.uretouch.feature.camera.logic.camera.api.CameraComponent
import com.uretouch.feature.camera.logic.photoPreview.api.PhotoPreviewComponent

interface CameraRootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Camera(val component: CameraComponent) : Child()
        class PhotoPreview(val component: PhotoPreviewComponent) : Child()
    }
}