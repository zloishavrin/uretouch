package com.uretouch.feature.history.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.uretouch.feature.history.logic.root.api.HistoryRootComponent

@Composable
fun HistoryRootScreen(
    component: HistoryRootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    Children(
        stack = childStack,
        animation = stackAnimation(slide()),
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is HistoryRootComponent.Child.History -> {
                HistoryScreen(
                    component = child.component
                )
            }
        }
    }
}