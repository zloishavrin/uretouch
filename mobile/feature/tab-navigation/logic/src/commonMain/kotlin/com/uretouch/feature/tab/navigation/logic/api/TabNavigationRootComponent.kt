package com.uretouch.feature.tab.navigation.logic.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.uretouch.feature.camera.logic.root.api.CameraRootComponent
import com.uretouch.feature.history.logic.root.api.HistoryRootComponent
import com.uretouch.feature.settings.logic.root.api.SettingsRootComponent

interface TabNavigationRootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class History(val component: HistoryRootComponent) : Child()
        class Camera(val component: CameraRootComponent) : Child()
        class Settings(val component: SettingsRootComponent) : Child()
    }

    fun onHistoryTabClick()
    fun onCameraTabClick()
    fun onSettingsTabClick()
}