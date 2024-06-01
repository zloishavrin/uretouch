import androidx.compose.ui.window.ComposeUIViewController
import com.uretouch.app.RootScreen
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { RootScreen(component = TODO()) }
