package com.uretouch.common.core.decompose

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.module.Module
import org.koin.core.scope.Scope

interface ClosableDiScope {
    val scope: Scope

    fun closeDiScope()
}

fun ComponentContext.defaultClosableScope(
    modules: List<Module>,
    rootScope: Scope? = null,
): Lazy<Scope> = lazy(mode = LazyThreadSafetyMode.NONE) {
    val closableScope = DefaultClosableDiScope(parentScope = rootScope, modules = modules)
    lifecycle.doOnDestroy(closableScope::closeDiScope)
    closableScope.scope
}

private class DefaultClosableDiScope(
    private val parentScope: Scope?,
    modules: List<Module>,
) : ClosableDiScope, KoinScopeComponent {
    override val scope: Scope = createScope()

    init {
        parentScope?.let { scope.linkTo(parentScope) }
        scope.getKoin().loadModules(modules)
    }

    override fun closeDiScope() {
        if (scope.isNotClosed()) {
            parentScope?.let { scope.unlink(parentScope) }
            scope.close()
        }
    }
}