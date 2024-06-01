package ${packageName}.api

import com.arkivanov.decompose.ComponentContext
import ${packageName}.internal.${defaultComponent}

object ${componentFactory} {
    fun create(
        componentContext: ComponentContext,
        dependencies: ${componentDependencies}
    ): ${component} {
        return ${defaultComponent}(
            componentContext = componentContext,
            dependencies = dependencies
        )
    }
}