package com.uretouch.common.ui.kit.compose.widget

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.theme.AppTheme

@Composable
fun AppCircularProgressIndicatorButton() {
    AppCircularProgressIndicator(
        color = AppTheme.colors.onPrimary,
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun AppCircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
) {
    CircularProgressIndicator(
        modifier = modifier,
        strokeWidth = 2.dp,
        color = color
    )
}