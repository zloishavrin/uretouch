package com.uretouch.feature.auth.logic.root.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.uretouch.feature.auth.logic.auth.api.AuthComponent
import com.uretouch.feature.auth.logic.checking.api.CheckingComponent
import com.uretouch.feature.auth.logic.registration.api.RegistrationComponent

interface AuthRootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Auth(val component: AuthComponent) : Child()
        class Registration(val component: RegistrationComponent) : Child()
        class Checking(val component: CheckingComponent) : Child()
    }
}