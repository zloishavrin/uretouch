package com.uretouch.feature.auth.logic.auth.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.common.core.flow.wrapToAny
import com.uretouch.feature.auth.logic.auth.api.AuthComponent
import com.uretouch.feature.auth.logic.auth.api.state.AuthUiState
import com.uretouch.feature.auth.logic.auth.api.state.toUiState
import com.uretouch.feature.auth.logic.auth.internal.di.AuthModule
import com.uretouch.feature.auth.logic.auth.internal.fsm.AuthFeature
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.AuthEmailChanged
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.AuthNavigationCompleted
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.AuthOnLoginClicked
import com.uretouch.feature.auth.logic.auth.internal.fsm.actions.AuthPasswordChanged
import com.uretouch.feature.auth.logic.auth.internal.fsm.state.AuthState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

internal class DefaultAuthComponent(
    componentContext: ComponentContext,
    authRootScope: Scope,
    private val navigateToRegistration: (email: String) -> Unit,
    private val navigateToCheck: (email: String, userId: String) -> Unit,
    private val navigateToTab: () -> Unit,
) : AuthComponent,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope(),
    ComponentContext by componentContext {

    private val scope by defaultClosableScope(
        rootScope = authRootScope,
        modules = listOf(AuthModule.module)
    )

    private val initialState = AuthState.initial()

    private val feature = instanceKeeper.getOrCreate {
        scope.get<AuthFeature>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<AuthUiState> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()

    init {
        feature.observeState()
            .filterIsInstance<AuthState.NavigationState>()
            .onEach { state ->
                when (state) {
                    is AuthState.NavigationState.OpenCheckEmail -> {
                        navigateToCheck(state.user.email, state.user.id)
                    }

                    is AuthState.NavigationState.OpenTab -> {
                        navigateToTab()
                    }
                }
            }
            .onEach { feature.proceed(AuthNavigationCompleted()) }
            .launchIn(coroutineScope)
    }

    override fun emailChange(text: String) {
        feature.proceed(AuthEmailChanged(email = text))
    }

    override fun passwordChange(text: String) {
        feature.proceed(AuthPasswordChanged(password = text))
    }

    override fun onLoginClick() {
        feature.proceed(AuthOnLoginClicked())
    }

    override fun onRegistrationClick() {
        navigateToRegistration(feature.getCurrentState().email)
    }
}