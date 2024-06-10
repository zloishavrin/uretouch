package com.uretouch.feature.camera.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.uretouch.common.ui.kit.animation.FadeScaleAnimatedVisibility
import com.uretouch.common.ui.kit.compose.widget.AppButton
import com.uretouch.common.ui.kit.compose.widget.AppCircularProgressIndicatorButton
import com.uretouch.common.ui.kit.compose.widget.AppTopBar
import com.uretouch.common.ui.kit.theme.AppColors
import com.uretouch.feature.camera.logic.photoPreview.api.PhotoPreviewComponent
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.camera.ui.generated.resources.Res
import uretouch.feature.camera.ui.generated.resources.photo_preview_cancel_photo
import uretouch.feature.camera.ui.generated.resources.photo_preview_prompt_placeholder
import uretouch.feature.camera.ui.generated.resources.photo_preview_send_photo

@Composable
fun PhotoPreviewScreen(
    component: PhotoPreviewComponent,
    modifier: Modifier = Modifier,
) {
    val componentState by component.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        component.showMessageFlow.collectLatest { (message, isError) ->
            snackbarHostState.showSnackbar(message = message)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            AppTopBar(title = "", onBackClick = component::onBackClick)
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
            )
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

                if (componentState.isGenerationModeVisible) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        componentState.generationModes.forEach {
                            val (backgroundColor, textColor) = if (it.id == componentState.selectedMode?.id) {
                                AppColors.White to AppColors.BackgroundDark
                            } else {
                                Color.Transparent to AppColors.White
                            }
                            val animatedBackgroundColor = animateColorAsState(backgroundColor)
                            val animatedTextColor = animateColorAsState(textColor)
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { component.onGenerationModeClick(it.id) }
                                    .background(animatedBackgroundColor.value)
                                    .padding(8.dp),
                                text = it.name,
                                color = animatedTextColor.value,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        value = componentState.prompt,
                        onValueChange = component::onPromptChange,
                        enabled = componentState.isCustomGenerationEnabled,
                        placeholder = {
                            Text(
                                text = stringResource(Res.string.photo_preview_prompt_placeholder),
                                fontSize = 14.sp
                            )
                        },
                        singleLine = true,
                    )
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
                    enabled = componentState.isSendEnabled
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        FadeScaleAnimatedVisibility(componentState.isUploadingPhoto) {
                            AppCircularProgressIndicatorButton()
                        }
                        FadeScaleAnimatedVisibility(!componentState.isUploadingPhoto) {
                            Text(text = stringResource(Res.string.photo_preview_send_photo))
                        }
                    }
                }
            }
        }
    }
}