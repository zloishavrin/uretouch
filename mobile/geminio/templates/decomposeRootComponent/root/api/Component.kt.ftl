package ${packageName}.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface ${component} {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {

    }
}