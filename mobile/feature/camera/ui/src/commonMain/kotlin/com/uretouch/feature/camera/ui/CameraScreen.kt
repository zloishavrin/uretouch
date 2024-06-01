package com.uretouch.feature.camera.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uretouch.feature.camera.logic.camera.api.CameraComponent

@Composable
internal fun CameraScreen(
    component: CameraComponent,
    modifier: Modifier = Modifier,
) {
    val componentState by component.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {

        }
    ) { scaffoldPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("Camera")
        }
    }
}