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
import com.uretouch.common.ui.kit.compose.widget.AppButton
import com.uretouch.common.ui.kit.compose.widget.LogoIcon
import com.uretouch.common.ui.kit.theme.AppTheme
import com.uretouch.feature.onboarding.logic.api.OnboardingComponent
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import uretouch.feature.onboarding.ui.generated.resources.Res
import uretouch.feature.onboarding.ui.generated.resources.onboarding_description
import uretouch.feature.onboarding.ui.generated.resources.onboarding_next

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
                LogoIcon(modifier = Modifier.fillMaxWidth())
            }
            Spacer(Modifier.height(16.dp))
            AnimatedVisibility(
                visible = visibleComponents.contains(ComponentScreen.Text),
                enter = fadeIn(tween(AnimationDurationDescription)) + expandVertically()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.onboarding_description),
                    style = AppTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(24.dp))
            AnimatedVisibility(
                visible = visibleComponents.contains(ComponentScreen.Button),
                enter = fadeIn(tween(AnimationDurationButton)) + expandVertically()
            ) {
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = component::onBeginClick,
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