package com.uretouch.feature.auth.ui.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.compose.widget.AppButton
import com.uretouch.common.ui.kit.compose.widget.LogoIcon
import com.uretouch.common.ui.kit.compose.widget.textfield.AppPasswordTextInput
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.auth.logic.auth.api.AuthComponent
import com.uretouch.feature.auth.logic.auth.api.state.AuthUiState
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.auth.ui.auth.generated.resources.Res
import uretouch.feature.auth.ui.auth.generated.resources.auth_email_label
import uretouch.feature.auth.ui.auth.generated.resources.auth_enter
import uretouch.feature.auth.ui.auth.generated.resources.auth_password_label
import uretouch.feature.auth.ui.auth.generated.resources.auth_registration

@Composable
fun AuthScreen(
    component: AuthComponent,
    modifier: Modifier = Modifier,
) {
    val componentState by component.state.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .imePadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(scaffoldPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = modifier.height(16.dp))
            LogoIcon()
            Spacer(modifier = modifier.height(16.dp))
            AuthEmailTextInput(
                componentState = componentState,
                onEmailChange = component::emailChange
            )
            Spacer(modifier = Modifier.height(8.dp))
            AuthPasswordTextInput(
                password = componentState.password,
                onPasswordChange = component::passwordChange,
                onDone = component::onLoginClick,
                isError = componentState.errorVisible
            )
            AnimatedVisibility(
                visible = componentState.errorVisible
            ) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = componentState.error,
                        color = AppTheme.colors.error
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = component::onLoginClick,
                enabled = componentState.enterButtonEnabled
            ) {
                Text(text = stringResource(Res.string.auth_enter))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(modifier = Modifier.weight(1f))
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = component::onRegistrationClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                ),
                elevation = null
            ) {
                Text(text = stringResource(Res.string.auth_registration))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun AuthPasswordTextInput(
    password: String,
    isError: Boolean,
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
            Text(stringResource(Res.string.auth_password_label))
        }
    )
}

@Composable
private fun AuthEmailTextInput(
    componentState: AuthUiState,
    onEmailChange: (email: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = componentState.email,
        onValueChange = onEmailChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Email
        ),
        isError = componentState.errorVisible,
        keyboardActions = KeyboardActions {
            focusManager.moveFocus(FocusDirection.Next)
        },
        label = {
            Text(stringResource(Res.string.auth_email_label))
        },
        singleLine = true
    )
}