package com.uretouch.feature.camera.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.preat.peekaboo.ui.camera.PeekabooCamera
import com.preat.peekaboo.ui.camera.rememberPeekabooCameraState
import com.uretouch.common.ui.kit.compose.widget.AppCircularProgressIndicator
import com.uretouch.common.ui.kit.theme.AppColors
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.camera.logic.camera.api.CameraComponent
import com.uretouch.feature.camera.ui.icons.MediaUiARecord64Regular
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.camera.ui.generated.resources.Res
import uretouch.feature.camera.ui.generated.resources.camera_permission_denied
import uretouch.feature.camera.ui.generated.resources.camera_saving
import uretouch.feature.camera.ui.generated.resources.camera_settings_open

@Composable
internal fun CameraScreen(
    component: CameraComponent,
    modifier: Modifier = Modifier,
) {
    val componentState by component.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { scaffoldPadding ->
        val cameraState = rememberPeekabooCameraState(onCapture = component::onPhotoCapture)

        Box(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
            contentAlignment = Alignment.Center
        ) {
            PeekabooCamera(
                modifier = Modifier.fillMaxSize(),
                state = cameraState,
                permissionDeniedContent = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.camera_permission_denied),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = component::onOpenSettingsClick) {
                            Text(text = stringResource(Res.string.camera_settings_open))
                        }
                    }
                },
            )

            if (cameraState.isCameraReady) {
                IconButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = { cameraState.capture() },
                    enabled = componentState.isEnabledPhoto && !cameraState.isCapturing
                ) {
                    Icon(
                        modifier = Modifier.size(80.dp),
                        imageVector = Icons.MediaUiARecord64Regular,
                        contentDescription = null,
                        tint = AppColors.White
                    )
                }
            }

        }

        if (componentState.isSaving || cameraState.isCapturing) {
            Dialog(
                onDismissRequest = {},
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = false,
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(AppTheme.colors.background, RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppCircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.camera_saving),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}