import androidx.compose.ui.window.ComposeUIViewController
import com.uretouch.app.RootScreen
import com.uretouch.feature.root.logic.api.RootComponent
import platform.UIKit.UIViewController

fun MainViewController(rootComponent: RootComponent): UIViewController = ComposeUIViewController {
    RootScreen(component = rootComponent)
}
