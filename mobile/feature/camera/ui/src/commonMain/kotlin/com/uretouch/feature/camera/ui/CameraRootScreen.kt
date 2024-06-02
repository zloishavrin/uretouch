package com.uretouch.feature.camera.ui

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.uretouch.common.ui.kit.utils.LocalBottomNavigationState
import com.uretouch.feature.camera.logic.root.api.CameraRootComponent

@Composable
fun CameraRootScreen(
    component: CameraRootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    val bottomNavigationState = LocalBottomNavigationState.current

    Children(
        stack = childStack,
        animation = stackAnimation(slide(orientation = Orientation.Vertical)),
        modifier = modifier
    ) {
        LaunchedEffect(it.instance) {
            when (it.instance) {
                is CameraRootComponent.Child.Camera -> bottomNavigationState.showBottomNavigation()
                is CameraRootComponent.Child.PhotoPreview -> bottomNavigationState.hideBottomNavigation(animateDuration = 0)
            }
        }
        when (val child = it.instance) {
            is CameraRootComponent.Child.Camera -> {
                CameraScreen(component = child.component)
            }

            is CameraRootComponent.Child.PhotoPreview -> {
                PhotoPreviewScreen(
                    component = child.component,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}