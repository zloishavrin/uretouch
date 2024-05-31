import androidx.compose.ui.window.ComposeUIViewController
import com.uretouch.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
