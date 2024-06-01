package ${packageName}.api.state

import ${packageName}.internal.fsm.state.${FSMState}

class ${componentState}(

)

internal fun ${FSMState}.toUiState(): ${componentState} {
    return when (this) {
        else -> TODO("Add ${FSMState} to ${componentState} mapper")
    }
}