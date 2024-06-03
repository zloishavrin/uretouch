package com.uretouch.common.ui.kit.compose.widget

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.icons.ArrowCLeft24Regular
import com.uretouch.common.ui.kit.theme.AppTheme

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = AppTheme.colors.background,
        elevation = 0.dp,
        title = {
            Text(text = title)
        },
        navigationIcon = if (onBackClick == null) {
            null
        } else {
            {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.ArrowCLeft24Regular,
                        contentDescription = null
                    )
                }
            }
        },
        actions = actions
    )
}