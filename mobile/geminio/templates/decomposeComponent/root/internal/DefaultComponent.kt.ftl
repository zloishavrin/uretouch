package ${packageName}.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.koin.dsl.module
import ${appPackage}.common.core.decompose.defaultClosableScope
import ${appPackage}.common.core.flow.AnyStateFlow
import ${appPackage}.common.core.environment.flow.MutableAnyStateFlow
import ${appPackage}.common.core.flow.wrapToAny
import ${appPackage}.common.coreDi.ComponentKoinContext
import ${packageName}.api.${component}
import ${packageName}.api.state.${componentState}
import ${packageName}.internal.di.${componentDiModule}
import ${packageName}.api.${componentDependencies}
import org.koin.core.scope.Scope

internal class ${defaultComponent}(
    componentContext: ComponentContext,
    rootScope: Scope,
    private val dependencies: ${componentDependencies},
) : ${component}, ComponentContext by componentContext {

    private val scope by defaultClosableScope(rootScope = rootScope, modules = listOf(${componentDiModule}.module))

    override val state: AnyStateFlow<${componentState}> = MutableAnyStateFlow(${componentState}.initial()).wrapToAny()
}