package ${packageName}.api

import ${appPackage}.common.core.flow.AnyStateFlow
import ${packageName}.api.state.${componentState}

interface ${component} {
     val state: AnyStateFlow<${componentState}>
}