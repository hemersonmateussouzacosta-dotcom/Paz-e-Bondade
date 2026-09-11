package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ParchmentColorScheme = lightColorScheme(
    primary = FranciscanBrown,
    onPrimary = Color.White,
    primaryContainer = FranciscanBrownLight.copy(alpha = 0.2f),
    onPrimaryContainer = FranciscanBrownDark,
    secondary = SeraphicGoldDark,
    onSecondary = Color.White,
    secondaryContainer = SeraphicGoldLight.copy(alpha = 0.25f),
    onSecondaryContainer = SeraphicGoldDark,
    tertiary = RubricRed,
    onTertiary = Color.White,
    tertiaryContainer = RubricRed.copy(alpha = 0.15f),
    onTertiaryContainer = RubricRedDark,
    background = ParchmentBackground,
    onBackground = ParchmentTextPrimary,
    surface = ParchmentSurface,
    onSurface = ParchmentTextPrimary,
    surfaceVariant = ParchmentSurfaceVariant,
    onSurfaceVariant = ParchmentTextSecondary,
    outline = FranciscanBrown.copy(alpha = 0.35f)
)

val DarkMonasticColorScheme = darkColorScheme(
    primary = SeraphicGoldLight,
    onPrimary = MonasticDarkBackground,
    primaryContainer = FranciscanBrown,
    onPrimaryContainer = SeraphicGoldLight,
    secondary = FranciscanBrownLight,
    onSecondary = Color.White,
    secondaryContainer = FranciscanBrownDark,
    onSecondaryContainer = SeraphicGoldLight,
    tertiary = RubricRedLight,
    onTertiary = Color.Black,
    tertiaryContainer = RubricRedDark,
    onTertiaryContainer = RubricRedLight,
    background = MonasticDarkBackground,
    onBackground = MonasticDarkTextPrimary,
    surface = MonasticDarkSurface,
    onSurface = MonasticDarkTextPrimary,
    surfaceVariant = MonasticDarkSurfaceVariant,
    onSurfaceVariant = MonasticDarkTextSecondary,
    outline = SeraphicGoldDark.copy(alpha = 0.4f)
)

val AlbaLightColorScheme = lightColorScheme(
    primary = FranciscanBrown,
    onPrimary = Color.White,
    primaryContainer = SeraphicGoldLight.copy(alpha = 0.2f),
    onPrimaryContainer = FranciscanBrownDark,
    secondary = SeraphicGoldDark,
    onSecondary = Color.White,
    secondaryContainer = SeraphicGoldLight.copy(alpha = 0.25f),
    onSecondaryContainer = SeraphicGoldDark,
    tertiary = RubricRed,
    onTertiary = Color.White,
    tertiaryContainer = RubricRed.copy(alpha = 0.12f),
    onTertiaryContainer = RubricRedDark,
    background = AlbaLightBackground,
    onBackground = AlbaLightTextPrimary,
    surface = AlbaLightSurface,
    onSurface = AlbaLightTextPrimary,
    surfaceVariant = AlbaLightSurfaceVariant,
    onSurfaceVariant = AlbaLightTextSecondary,
    outline = Color.LightGray
)

@Composable
fun MyApplicationTheme(
    readingMode: ReadingThemeMode = ReadingThemeMode.PARCHMENT,
    fontSizeScale: FontSizeScale = FontSizeScale.MEDIUM,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when (readingMode) {
        ReadingThemeMode.PARCHMENT -> ParchmentColorScheme
        ReadingThemeMode.DARK_MONASTIC -> DarkMonasticColorScheme
        ReadingThemeMode.ALBA_LIGHT -> AlbaLightColorScheme
    }

    val typography = createPaxTypography(fontSizeScale.scaleFactor)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
