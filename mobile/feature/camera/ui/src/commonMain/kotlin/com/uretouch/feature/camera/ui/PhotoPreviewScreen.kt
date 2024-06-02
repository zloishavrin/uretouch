package com.uretouch.feature.camera.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.uretouch.common.ui.kit.animation.FadeScaleAnimatedVisibility
import com.uretouch.common.ui.kit.compose.widget.AppButton
import com.uretouch.common.ui.kit.compose.widget.AppCircularProgressIndicatorButton
import com.uretouch.common.ui.kit.compose.widget.AppTopBar
import com.uretouch.feature.camera.logic.photoPreview.api.PhotoPreviewComponent
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.camera.ui.generated.resources.Res
import uretouch.feature.camera.ui.generated.resources.photo_preview_cancel_photo
import uretouch.feature.camera.ui.generated.resources.photo_preview_send_photo

@Composable
fun PhotoPreviewScreen(
    component: PhotoPreviewComponent,
    modifier: Modifier = Modifier,
) {
    val componentState by component.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            AppTopBar(title = "", onBackClick = component::onBackClick)
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
        ) {
            Column(
                modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                AsyncImage(
                    model = componentState.photoPath,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text("Студия")
                    Text("Природа")
                    Text("Свой")
                }
                AnimatedVisibility(visible = componentState.isCustomPromptVisible) {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            value = componentState.prompt,
                            onValueChange = component::onPromptChange,
                            singleLine = true,
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButton(
                    modifier = Modifier.weight(1f),
                    onClick = component::onCancelClick,
                    elevation = null,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    )
                ) {
                    Text(text = stringResource(Res.string.photo_preview_cancel_photo))
                }
                Spacer(modifier = Modifier.width(8.dp))
                AppButton(
                    modifier = Modifier.weight(1f),
                    onClick = component::onProcessPhotoClick,
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        FadeScaleAnimatedVisibility(componentState.isLoading) {
                            AppCircularProgressIndicatorButton()
                        }
                        FadeScaleAnimatedVisibility(!componentState.isLoading) {
                            Text(text = stringResource(Res.string.photo_preview_send_photo))
                        }
                    }
                }
            }
        }
    }
}