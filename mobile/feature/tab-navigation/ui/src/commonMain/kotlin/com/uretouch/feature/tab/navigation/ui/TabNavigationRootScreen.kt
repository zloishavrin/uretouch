package com.uretouch.feature.tab.navigation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.uretouch.common.ui.kit.utils.LocalBottomNavigationState
import com.uretouch.feature.camera.ui.CameraRootScreen
import com.uretouch.feature.history.ui.HistoryRootScreen
import com.uretouch.feature.settings.ui.SettingsRootScreen
import com.uretouch.feature.tab.navigation.logic.api.TabNavigationRootComponent

@Composable
fun TabNavigationRootScreen(
    component: TabNavigationRootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    val childInstance = childStack.active.instance
    val bottomNavigationState = LocalBottomNavigationState.current
    val currentBottomNavigationState by bottomNavigationState.state.collectAsState()
    val onTabClick: ((NavigationTab) -> Unit) = remember(component) {
        { tab ->
            when (tab) {
                NavigationTab.History -> component.onHistoryTabClick()
                NavigationTab.Camera -> component.onCameraTabClick()
                NavigationTab.Settings -> component.onSettingsTabClick()
            }
        }
    }
    val activeTab: NavigationTab = remember(childInstance) {
        when (childInstance) {
            is TabNavigationRootComponent.Child.Camera -> NavigationTab.Camera
            is TabNavigationRootComponent.Child.History -> NavigationTab.History
            is TabNavigationRootComponent.Child.Settings -> NavigationTab.Settings
        }
    }
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = currentBottomNavigationState.isVisible,
                enter = slideInVertically(initialOffsetY = { it / 2 }, animationSpec = tween(currentBottomNavigationState.animateDuration.toInt())),
                exit = slideOutVertically(targetOffsetY = { it / 2 }, animationSpec = tween(currentBottomNavigationState.animateDuration.toInt()))
            ) {
                AppBottomNavigation(
                    modifier = Modifier,
                    activeTab = activeTab,
                    onTabClick = onTabClick,
                )
            }
        },
        modifier = Modifier.navigationBarsPadding()
    ) { scaffoldPadding ->
        Children(
            stack = childStack,
            animation = stackAnimation(),
            modifier = modifier.padding(scaffoldPadding)
        ) {
            when (val child = it.instance) {
                is TabNavigationRootComponent.Child.Camera -> {
                    CameraRootScreen(
                        component = child.component
                    )
                }

                is TabNavigationRootComponent.Child.History -> {
                    HistoryRootScreen(
                        component = child.component
                    )
                }

                is TabNavigationRootComponent.Child.Settings -> {
                    SettingsRootScreen(
                        component = child.component
                    )
                }
            }
        }
    }
}