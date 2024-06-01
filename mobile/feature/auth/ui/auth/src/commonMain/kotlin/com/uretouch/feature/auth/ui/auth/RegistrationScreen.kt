package com.uretouch.feature.auth.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.animation.FadeScaleAnimatedVisibility
import com.uretouch.common.ui.kit.compose.widget.AppButton
import com.uretouch.common.ui.kit.compose.widget.AppTopBar
import com.uretouch.common.ui.kit.compose.widget.LogoIcon
import com.uretouch.common.ui.kit.compose.widget.textfield.AppPasswordTextInput
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.auth.logic.registration.api.RegistrationComponent
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.auth.ui.auth.generated.resources.Res
import uretouch.feature.auth.ui.auth.generated.resources.registration_email_label
import uretouch.feature.auth.ui.auth.generated.resources.registration_password_label
import uretouch.feature.auth.ui.auth.generated.resources.registration_registry
import uretouch.feature.auth.ui.auth.generated.resources.registration_repeat_password_label
import uretouch.feature.auth.ui.auth.generated.resources.registration_title

@Composable
fun RegistrationScreen(
    component: RegistrationComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        topBar = {
            AppTopBar(
                title = stringResource(Res.string.registration_title),
                onBackClick = component::onBackClick
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(scaffoldPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = modifier.height(16.dp))
            LogoIcon()
            Spacer(modifier = modifier.height(16.dp))
            RegistrationEmailTextInput(
                email = state.email,
                isError = state.errorVisible,
                onEmailChange = component::emailChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            RegistrationPasswordTextInput(
                password = state.password,
                onPasswordChange = component::passwordChange,
                isError = state.errorVisible,
                onDone = {},
                labelRes = Res.string.registration_password_label
            )
            Spacer(modifier = Modifier.height(8.dp))
            RegistrationPasswordTextInput(
                password = state.repeatPassword,
                onPasswordChange = component::repeatPasswordChange,
                isError = state.errorVisible,
                onDone = {},
                labelRes = Res.string.registration_repeat_password_label
            )
            if (state.errorVisible) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.error,
                    color = AppTheme.colors.error
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            AppButton(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                onClick = component::onRegistryClick,
                enabled = state.registryEnabled
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    FadeScaleAnimatedVisibility(visible = state.isLoading) {
                        CircularProgressIndicator(
                            color = AppTheme.colors.onPrimary,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    FadeScaleAnimatedVisibility(visible = !state.isLoading) {
                        Text(text = stringResource(Res.string.registration_registry))
                    }
                }
            }
        }
    }
}

@Composable
private fun RegistrationPasswordTextInput(
    password: String,
    isError: Boolean,
    labelRes: StringResource,
    onPasswordChange: (String) -> Unit,
    onDone: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    AppPasswordTextInput(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = onPasswordChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        isError = isError,
        keyboardActions = KeyboardActions {
            onDone()
            keyboardController?.hide()
        },
        label = {
            Text(stringResource(labelRes))
        }
    )
}

@Composable
private fun RegistrationEmailTextInput(
    email: String,
    isError: Boolean,
    onEmailChange: (email: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        onValueChange = onEmailChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Email
        ),
        isError = isError,
        keyboardActions = KeyboardActions {
            focusManager.moveFocus(FocusDirection.Next)
        },
        label = {
            Text(stringResource(Res.string.registration_email_label))
        }
    )
}