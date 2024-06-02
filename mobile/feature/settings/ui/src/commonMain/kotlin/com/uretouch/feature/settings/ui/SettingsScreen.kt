package com.uretouch.feature.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.resources.app_name
import com.uretouch.common.ui.kit.resources.ic_logo
import com.uretouch.common.ui.kit.theme.AppColors
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.settings.logic.settings.api.SettingsComponent
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import uretouch.feature.settings.ui.generated.resources.Res
import uretouch.feature.settings.ui.generated.resources.settings_logout
import uretouch.feature.settings.ui.generated.resources.settings_version
import com.uretouch.common.ui.kit.resources.Res as UiKitRes

@Composable
internal fun SettingsScreen(
    component: SettingsComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = vectorResource(UiKitRes.drawable.ic_logo),
                contentDescription = null,
                tint = AppColors.Yellow
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(UiKitRes.string.app_name),
                style = AppTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(Res.string.settings_version),
                style = AppTheme.typography.body1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth().clickable(onClick = component::onLogoutClick).padding(16.dp),
                text = stringResource(Res.string.settings_logout)
            )
        }
    }
}