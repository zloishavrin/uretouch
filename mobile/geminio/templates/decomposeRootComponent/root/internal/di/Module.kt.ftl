package ${packageName}.internal.di

import org.koin.core.module.Module
import org.koin.dsl.module
import ${packageName}.api.${componentDependencies}

internal fun create${componentDiModule}(dependencies: ${componentDependencies}): List<Module> {
    return listOf(
        module {

        }
    )
}