package com.uretouch.feature.auth.ui.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.uretouch.feature.auth.logic.root.api.AuthRootComponent
import com.uretouch.feature.auth.ui.auth.AuthScreen

@Composable
fun AuthRootScreen(
    component: AuthRootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is AuthRootComponent.Child.Auth -> {
                AuthScreen(
                    component = child.component,
                )
            }
        }
    }
}