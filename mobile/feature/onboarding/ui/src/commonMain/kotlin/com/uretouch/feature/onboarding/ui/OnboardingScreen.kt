package com.uretouch.feature.onboarding.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uretouch.common.ui.kit.resources.*
import com.uretouch.common.ui.kit.theme.AppColors
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.onboarding.logic.api.OnboardingComponent
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import uretouch.feature.onboarding.ui.generated.resources.Res
import com.uretouch.common.ui.kit.resources.Res as UiKitRes
import uretouch.feature.onboarding.ui.generated.resources.onboarding_description
import uretouch.feature.onboarding.ui.generated.resources.onboarding_next
import uretouch.feature.onboarding.ui.generated.resources.onboarding_title

private enum class ComponentScreen {
    Logo, Text, Button
}

@Composable
fun OnboardingScreen(
    component: OnboardingComponent,
    modifier: Modifier = Modifier,
) {
    var visibleComponents by rememberSaveable { mutableStateOf(setOf<ComponentScreen>()) }
    LaunchedEffect(Unit) {
        delay(200)
        visibleComponents += ComponentScreen.Logo
        delay(AnimationScaleInDurationLogo.toLong())
        visibleComponents += ComponentScreen.Text
        visibleComponents += ComponentScreen.Button
    }
    Scaffold(
        modifier = modifier.fillMaxSize().statusBarsPadding(),
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .padding(scaffoldPadding)
                .padding(horizontal = 24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = visibleComponents.contains(ComponentScreen.Logo),
                enter = fadeIn(tween(AnimationFadeInDurationLogo)) + scaleIn(tween(AnimationScaleInDurationLogo))
            ) {
                Icon(
                    imageVector = vectorResource(UiKitRes.drawable.ic_logo),
                    contentDescription = null,
                    tint = AppColors.Yellow
                )
            }

            AnimatedVisibility(
                visible = visibleComponents.contains(ComponentScreen.Text),
                enter = fadeIn(tween(AnimationDurationDescription)) + expandVertically()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.onboarding_title),
                        style = AppTheme.typography.h4,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.onboarding_description),
                        style = AppTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }

            }
            Spacer(Modifier.height(24.dp))
            AnimatedVisibility(
                visible = visibleComponents.contains(ComponentScreen.Button),
                enter = fadeIn(tween(AnimationDurationButton)) + expandVertically()
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = component::onBeginClick,
                    shape = AppTheme.shapes.medium
                ) {
                    Text(text = stringResource(Res.string.onboarding_next))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

private const val AnimationFadeInDurationLogo = 500
private const val AnimationScaleInDurationLogo = 1000
private const val AnimationDurationDescription = 1000
private const val AnimationDurationButton = 2000