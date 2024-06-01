package ${packageName}.internal.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ${packageName}.internal.fsm.${FSMAsyncWorker}
import ${packageName}.internal.fsm.${FSMFeature}

internal val ${componentDiModule} = module {
    factoryOf(::${FSMAsyncWorker})
    factory { initialState ->
        ${FSMFeature}(
            initialState = initialState.get(),
            asyncWorker = get(),
        )
    }
}