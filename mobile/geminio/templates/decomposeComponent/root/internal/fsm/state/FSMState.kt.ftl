package ${packageName}.internal.fsm.state

import ru.kontur.mobile.visualfsm.State

internal sealed class ${FSMState} : State {

    sealed class AsyncWorkerState : ${FSMState}() {

    }

    sealed class NavigationState : ${FSMState}() {

    }

    companion object {
        fun initial(): ${FSMState} {
            return TODO("Add initial ${FSMState}")
        }
    }
}