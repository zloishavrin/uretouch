package com.uretouch.feature.auth.logic.checking.internal

import com.arkivanov.decompose.ComponentContext
import com.uretouch.common.core.decompose.CancelableCoroutineScope
import com.uretouch.common.core.decompose.cancelableCoroutineScope
import com.uretouch.common.core.decompose.defaultClosableScope
import com.uretouch.domain.auth.interactor.AuthInteractor
import com.uretouch.feature.auth.logic.checking.api.CheckingComponent
import com.uretouch.feature.auth.logic.checking.internal.di.CheckingModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.scope.Scope

internal class DefaultCheckingComponent(
    componentContext: ComponentContext,
    rootScope: Scope,
    override val email: String,
    private val userId: String,
    private val navigateToTab: () -> Unit,
    private val navigateBack: () -> Unit,
) : CheckingComponent,
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(CheckingModule.module))

    private val authInteractor = scope.get<AuthInteractor>()

    private val pooling = flow {
        while (true) {
            emit(authInteractor.checkActiveStatus(userId = userId))
            delay(PoolingDelay)
        }
    }

    init {
        pooling.filter { isActive -> isActive }
            .onEach { navigateToTab() }
            .launchIn(coroutineScope)
    }

    override fun onBackClick() {
        navigateBack()
    }
}

private const val PoolingDelay = 5000L