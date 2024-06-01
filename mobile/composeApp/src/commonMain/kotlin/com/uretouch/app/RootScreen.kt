package com.uretouch.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.auth.ui.root.AuthRootScreen
import com.uretouch.feature.onboarding.ui.OnboardingScreen
import com.uretouch.feature.root.logic.api.RootComponent
import com.uretouch.feature.tab.navigation.ui.TabNavigationRootScreen

@Composable
internal fun RootScreen(
    component: RootComponent,
) {
    AppTheme {
        val childStack by component.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide()),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.AuthRoot -> {
                    AuthRootScreen(
                        component = child.component
                    )
                }

                is RootComponent.Child.Onboarding -> {
                    OnboardingScreen(
                        component = child.component
                    )
                }

                is RootComponent.Child.TabNavigationRoot -> {
                    TabNavigationRootScreen(
                        component = child.component
                    )
                }
            }
        }
    }
}

internal expect fun openUrl(url: String?)