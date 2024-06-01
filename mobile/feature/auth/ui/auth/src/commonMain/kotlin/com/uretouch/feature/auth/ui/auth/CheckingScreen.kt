package com.uretouch.feature.auth.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.compose.widget.AppTopBar
import com.uretouch.common.ui.kit.compose.widget.LogoIcon
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.auth.logic.checking.api.CheckingComponent
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.auth.ui.auth.generated.resources.Res
import uretouch.feature.auth.ui.auth.generated.resources.checking_description

@Composable
fun CheckingScreen(
    component: CheckingComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            AppTopBar(
                title = "",
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
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(Res.string.checking_description, component.email),
                style = AppTheme.typography.h6
            )
            Spacer(modifier = modifier.height(16.dp))
        }
    }
}