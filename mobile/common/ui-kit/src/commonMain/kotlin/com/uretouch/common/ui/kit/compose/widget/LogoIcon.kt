package com.uretouch.common.ui.kit.compose.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.resources.Res
import com.uretouch.common.ui.kit.resources.app_name
import com.uretouch.common.ui.kit.resources.ic_logo
import com.uretouch.common.ui.kit.theme.AppColors
import com.uretouch.common.ui.kit.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun LogoIcon(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_logo),
            contentDescription = null,
            tint = AppColors.Yellow
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.app_name),
            style = AppTheme.typography.h4,
            textAlign = TextAlign.Center
        )
    }
}