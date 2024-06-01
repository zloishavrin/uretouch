package ${packageName}.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ${appPackage}.common.core.decompose.CancelableCoroutineScope
import ${appPackage}.common.core.decompose.cancelableCoroutineScope
import ${appPackage}.common.core.decompose.defaultClosableScope
import ${appPackage}.common.core.fsm.NavigationComponent
import ${appPackage}.common.core.flow.AnyStateFlow
import ${appPackage}.common.core.flow.wrapToAny
import ${appPackage}.common.coreDi.ComponentKoinContext
import ${packageName}.api.${component}
import ${packageName}.api.${componentDependencies}
import ${packageName}.api.state.${componentState}
import ${packageName}.api.state.toUiState
import ${packageName}.internal.di.${componentDiModule}
import ${packageName}.internal.fsm.${FSMFeature}
import ${packageName}.internal.fsm.state.${FSMState}
import org.koin.core.scope.Scope

internal class ${defaultComponent}(
    componentContext: ComponentContext,
    rootScope:Scope,
    private val dependencies: ${componentDependencies},
) : ${component},
    ComponentContext by componentContext,
    CancelableCoroutineScope by componentContext.cancelableCoroutineScope() {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(${componentDiModule}.module))

    private val initialState = ${FSMState}.initial()

    private val feature = instanceKeeper.getOrCreate {
        scope.get<${FSMFeature}>(parameters = { parametersOf(initialState) })
    }

    override val state: AnyStateFlow<${componentState}> = feature.observeState()
        .map { it.toUiState() }
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.Eagerly,
            initialValue = initialState.toUiState()
        ).wrapToAny()
}