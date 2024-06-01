package ${packageName}

import ru.kontur.mobile.visualfsm.Transition

internal class <#if isFromAsyncWorker>${actionNameFromAsyncWorker}<#else>${actionName}</#if>() : Base${feature}Action() {
    inner class Blank : Transition<${feature}State, ${feature}State>() {
        override fun transform(state: ${feature}State): ${feature}State {
            return ${feature}State
        }
    }
}