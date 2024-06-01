package com.uretouch.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.uretouch.feature.root.logic.api.RootComponent
import com.uretouch.feature.root.logic.api.RootComponentFactory
import com.uretouch.feature.root.logic.api.RootDependencies
import org.koin.android.ext.android.get


class AppActivity : ComponentActivity() {
    private var rootComponent: RootComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        rootComponent = RootComponentFactory.create(
            componentContext = defaultComponentContext(),
            dependencies = get()
        )

        setContent {
            RootScreen(requireNotNull(rootComponent))
        }
    }
}

internal actual fun openUrl(url: String?) {
    val uri = url?.let { Uri.parse(it) } ?: return
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    AndroidApp.INSTANCE.startActivity(intent)
}