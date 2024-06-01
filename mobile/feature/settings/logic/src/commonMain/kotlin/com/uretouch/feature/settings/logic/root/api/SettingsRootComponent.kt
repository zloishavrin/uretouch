package com.uretouch.feature.settings.logic.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.uretouch.feature.settings.logic.settings.api.SettingsComponent

interface SettingsRootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Settings(val component: SettingsComponent) : Child()
    }
}