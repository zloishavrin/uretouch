package ${packageName}.internal.fsm

import ${packageName}.internal.fsm.actions.${FSMBaseAction}
import ${packageName}.internal.fsm.state.${FSMState}
import ru.kontur.mobile.visualfsm.AsyncWorker
import ru.kontur.mobile.visualfsm.AsyncWorkerTask

internal class ${FSMAsyncWorker}() : AsyncWorker<${FSMState}, ${FSMBaseAction}>() {
    override fun onNextState(state: ${FSMState}): AsyncWorkerTask<${FSMState}> {
        return when (state) {
            else -> AsyncWorkerTask.Cancel()
        }
    }
}