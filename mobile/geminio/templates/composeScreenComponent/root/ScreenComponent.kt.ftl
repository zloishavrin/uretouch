package ${packageName}

import androidx.compose.foundation.layout.fillMaxSize
import ru.kontur.mobile.compose.design.widget.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun ${screenName}(
    component: ${component},
    modifier: Modifier = Modifier
) {
    val componentState by component.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {

        }
    ) { scaffoldPadding ->

    }
}