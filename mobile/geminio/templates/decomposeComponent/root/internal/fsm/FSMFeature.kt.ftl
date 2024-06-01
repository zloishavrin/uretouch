package ${packageName}.internal.fsm

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import ${packageName}.internal.fsm.actions.${FSMBaseAction}
import ${packageName}.internal.fsm.state.${FSMState}
import ru.kontur.mobile.visualfsm.Feature
import ru.kontur.mobile.visualfsm.GenerateTransitionsFactory

@GenerateTransitionsFactory
internal class ${FSMFeature}(
    initialState: ${FSMState},
    private val asyncWorker: ${FSMAsyncWorker},
) : InstanceKeeper.Instance, Feature<${FSMState}, ${FSMBaseAction}>(
    initialState = initialState,
    asyncWorker = asyncWorker,
    transitionsFactory = Generated${FSMFeature}TransitionsFactory(),
) {
    override fun onDestroy() {
        asyncWorker.unbind()
    }
}