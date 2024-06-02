package com.uretouch.feature.history.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.uretouch.common.ui.kit.compose.widget.AppButton
import com.uretouch.common.ui.kit.compose.widget.AppCircularProgressIndicator
import com.uretouch.common.ui.kit.compose.widget.AppTopBar
import com.uretouch.common.ui.kit.theme.AppColors
import com.uretouch.feature.history.logic.history.api.HistoryComponent
import com.uretouch.feature.history.logic.history.api.state.GenerationUi
import com.uretouch.feature.history.logic.history.api.state.HistoryUiState
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.history.ui.generated.resources.Res
import uretouch.feature.history.ui.generated.resources.history_loading_error
import uretouch.feature.history.ui.generated.resources.history_repeat
import uretouch.feature.history.ui.generated.resources.history_title

@Composable
internal fun HistoryScreen(
    component: HistoryComponent,
    modifier: Modifier = Modifier,
) {
    val componentState by component.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            AppTopBar(title = stringResource(Res.string.history_title))
        }
    ) { scaffoldPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding)
        ) {
            when (val state = componentState) {
                HistoryUiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center).padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(Res.string.history_loading_error))
                        Spacer(modifier = Modifier.height(24.dp))
                        AppButton(
                            onClick = component::onRefresh
                        ) {
                            Text(text = stringResource(Res.string.history_repeat))
                        }
                    }

                }

                is HistoryUiState.Loaded -> {
                    LoadedContent(
                        modifier = Modifier.fillMaxSize().padding(scaffoldPadding),
                        state = state
                    )
                }

                HistoryUiState.Loading -> {
                    AppCircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }


    }
}

@Composable
private fun LoadedContent(
    state: HistoryUiState.Loaded,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(state.generations) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, color = AppColors.Yellow, RoundedCornerShape(8.dp)),
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = it.url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                if (it.status == GenerationUi.Status.InProgress) {
                    AppCircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

        }
    }
}