package com.uretouch.feature.auth.logic.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.uretouch.feature.auth.logic.auth.api.AuthComponent

interface AuthRootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Auth(val component: AuthComponent) : Child()
    }
}