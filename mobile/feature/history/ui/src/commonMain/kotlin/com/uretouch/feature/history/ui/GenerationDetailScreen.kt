package com.uretouch.feature.history.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.uretouch.common.ui.kit.compose.widget.AppCircularProgressIndicator
import com.uretouch.common.ui.kit.compose.widget.AppTopBar
import com.uretouch.common.ui.kit.theme.AppColors
import com.uretouch.feature.history.logic.generationDetail.api.GenerationDetailComponent
import com.uretouch.feature.history.logic.generationDetail.api.state.GenerationDetailUiState
import com.uretouch.feature.history.ui.icons.NetDownload24Solid
import com.uretouch.feature.history.ui.widget.ErrorContent
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.history.ui.generated.resources.Res
import uretouch.feature.history.ui.generated.resources.history_loading_error

@Composable
fun GenerationDetailScreen(
    component: GenerationDetailComponent,
    modifier: Modifier = Modifier,
) {
    val componentState by component.state.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            AppTopBar(
                modifier = Modifier.fillMaxWidth(),
                title = "",
                onBackClick = component::onBackClick,
                actions = {
                    IconButton(
                        onClick = { component.onDownloadAllGenerationsClick() }
                    ) {
                        Icon(
                            imageVector = Icons.NetDownload24Solid,
                            contentDescription = null,
                            tint = AppColors.White
                        )
                    }
                }
            )
        }
    ) { scaffoldPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(scaffoldPadding)
        ) {
            when (val state = componentState) {
                GenerationDetailUiState.Error -> {
                    ErrorContent(
                        modifier = Modifier.align(Alignment.Center).padding(horizontal = 16.dp),
                        title = stringResource(Res.string.history_loading_error),
                        onRetryClick = component::onRefresh
                    )
                }

                is GenerationDetailUiState.Loaded -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                            text = "Запрос: ${state.generationUi.prompt}"
                        )
                        LazyVerticalGrid(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(state.generationUi.generationUrls) {
                                Box(
                                    modifier = Modifier.animateContentSize()
                                ) {
                                    AsyncImage(
                                        modifier = Modifier.fillMaxWidth(),
                                        model = it,
                                        contentDescription = null,
                                    )

                                    Column(
                                        modifier = Modifier.align(Alignment.BottomEnd)
                                    ) {
                                        Icon(
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .background(AppColors.Yellow)
                                                .clickable { component.onShareClick(it) }
                                                .padding(8.dp),
                                            imageVector = Icons.Default.Share,
                                            contentDescription = null,
                                            tint = AppColors.White
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Icon(
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .background(AppColors.Yellow)
                                                .clickable { component.onDownloadGenerationClick(it) }
                                                .padding(8.dp),
                                            imageVector = Icons.NetDownload24Solid,
                                            contentDescription = null,
                                            tint = AppColors.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                GenerationDetailUiState.Loading -> {
                    AppCircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}