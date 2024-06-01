package ${packageName}.internal

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.serialization.Serializable
import ${appPackage}.common.coreDi.ComponentKoinContext
import ${packageName}.api.${component}
import ${packageName}.api.${component}.Child
import ${packageName}.api.${componentDependencies}
import ${packageName}.internal.di.create${componentDiModule}

internal class ${defaultComponent}(
    componentContext: ComponentContext,
    dependencies: ${componentDependencies},
) : ${component}, ComponentContext by componentContext {
    private val koinContext = instanceKeeper.getOrCreate { ComponentKoinContext() }
    private val scope = koinContext.getOrCreateKoinScope(create${componentDiModule}(dependencies))

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialStack = { listOf() },
        childFactory = ::child,
        handleBackButton = true
    )

    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child {
        return when (config) {

        }
    }

    @Serializable
    private sealed interface Config {

    }
}