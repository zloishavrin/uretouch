package com.uretouch.common.ui.kit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.uretouch.common.ui.kit.resources.MontserratAlternates_Regular
import com.uretouch.common.ui.kit.resources.Res
import org.jetbrains.compose.resources.Font

private val LightColorScheme = lightColors(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    error = md_theme_light_error,
    onError = md_theme_light_onError,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
)

private val DarkColorScheme = darkColors(
    primary = AppColors.Yellow,
    onPrimary = AppColors.White,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    error = AppColors.RedDark,
    onError = md_theme_dark_onError,
    background = AppColors.BackgroundDark,
    onBackground = AppColors.White,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
)

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

@Composable
fun AppTheme(
    content: @Composable() () -> Unit,
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState
    ) {
        val isDark by isDarkState
        SystemAppearance(isDark = true)
        MaterialTheme(
            colors = DarkColorScheme,
            content = {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AppTheme.colors.background,
                    content = content
                )
            },
            typography = MaterialTheme.typography.copy(
                h1 = MaterialTheme.typography.h1.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                h2 = MaterialTheme.typography.h2.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                h3 = MaterialTheme.typography.h3.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                h4 = MaterialTheme.typography.h4.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                h5 = MaterialTheme.typography.h5.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                h6 = MaterialTheme.typography.h6.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                subtitle1 = MaterialTheme.typography.subtitle1.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                subtitle2 = MaterialTheme.typography.subtitle2.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                body1 = MaterialTheme.typography.body1.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                body2 = MaterialTheme.typography.body2.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                button = MaterialTheme.typography.button.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular)),
                    fontSize = 16.sp
                ),
                caption = MaterialTheme.typography.caption.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),
                overline = MaterialTheme.typography.overline.copy(
                    fontFamily = FontFamily(Font(Res.font.MontserratAlternates_Regular))
                ),

                )
        )
    }
}

object AppTheme {
    val colors: Colors
        @Composable
        get() = MaterialTheme.colors

    val typography: Typography
        @Composable
        get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)
