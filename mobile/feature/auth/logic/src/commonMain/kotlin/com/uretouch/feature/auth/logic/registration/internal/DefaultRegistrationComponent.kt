package com.uretouch.feature.auth.logic.registration.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.common.core.flow.AnyStateFlow
import com.uretouch.common.core.flow.wrapToAny
import com.uretouch.feature.auth.logic.registration.api.RegistrationComponent
import com.uretouch.feature.auth.logic.registration.api.state.RegistrationUiState
import com.uretouch.feature.auth.logic.registration.api.state.toUiState
import com.uretouch.feature.auth.logic.registration.internal.di.RegistrationModule
import com.uretouch.feature.auth.logic.registration.internal.fsm.RegistrationFeature
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.RegistrationEmailChanged
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.RegistrationOnRegistryClicked
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.RegistrationPasswordChanged
import com.uretouch.feature.auth.logic.registration.internal.fsm.actions.RegistrationRepeatPasswordChanged
import com.uretouch.feature.auth.logic.registration.internal.fsm.state.RegistrationState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

internal class DefaultRegistrationComponent(
    componentContext: ComponentContext,
    rootScope: Scope,
    email: String,
    private val navigateToCheckEmail: (email: String, userId: String) -> Unit,
    private val navigateBack: () -> Unit,
) : RegistrationComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(RegistrationModule.module))

    private val initialState = RegistrationState.initial(email)

    private val feature = instanceKeeper.getOrCreate {
        scope.get<RegistrationFeature>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<RegistrationUiState> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()

    init {
        feature.observeState()
            .filterIsInstance<RegistrationState.NavigationState>()
            .onEach { state ->
                when (state) {
                    is RegistrationState.NavigationState.ToCheckEmail -> {
                        navigateToCheckEmail(state.user.email, state.user.id)
                    }
                }
            }.launchIn(coroutineScope)
    }

    override fun passwordChange(password: String) {
        feature.proceed(RegistrationPasswordChanged(password = password))
    }

    override fun repeatPasswordChange(password: String) {
        feature.proceed(RegistrationRepeatPasswordChanged(password = password))
    }

    override fun emailChange(email: String) {
        feature.proceed(RegistrationEmailChanged(email = email))
    }

    override fun onRegistryClick() {
        feature.proceed(RegistrationOnRegistryClicked())
    }

    override fun onBackClick() {
        navigateBack()
    }
}