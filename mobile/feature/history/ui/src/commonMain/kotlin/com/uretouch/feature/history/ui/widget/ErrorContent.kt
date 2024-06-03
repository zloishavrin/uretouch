package com.uretouch.feature.history.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.compose.widget.AppButton
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.history.ui.generated.resources.Res
import uretouch.feature.history.ui.generated.resources.history_repeat

@Composable
fun ErrorContent(
    title: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Spacer(modifier = Modifier.height(24.dp))
        AppButton(
            onClick = onRetryClick
        ) {
            Text(text = stringResource(Res.string.history_repeat))
        }
    }
}