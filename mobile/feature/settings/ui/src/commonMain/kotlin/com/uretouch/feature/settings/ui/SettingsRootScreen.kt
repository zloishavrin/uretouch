package com.uretouch.feature.settings.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.uretouch.feature.settings.logic.root.api.SettingsRootComponent

@Composable
fun SettingsRootScreen(
    component: SettingsRootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is SettingsRootComponent.Child.Settings -> {
                SettingsScreen(
                    component = child.component
                )
            }
        }
    }
}