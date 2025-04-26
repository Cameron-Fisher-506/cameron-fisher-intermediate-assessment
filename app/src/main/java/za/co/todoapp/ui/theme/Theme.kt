package za.co.todoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val darkColorPalette = darkColorScheme(
    primary = ThemeColors.Dark.primary,
    onPrimary = ThemeColors.Dark.onPrimary,
    primaryContainer = ThemeColors.Dark.primaryContainer,
    onPrimaryContainer = ThemeColors.Dark.onPrimaryContainer,

    secondary = ThemeColors.Dark.secondary,
    onSecondary = ThemeColors.Dark.onSecondary,
    secondaryContainer = ThemeColors.Dark.secondaryContainer,
    onSecondaryContainer = ThemeColors.Dark.onSecondaryContainer,

    tertiary = ThemeColors.Dark.tertiary,
    onTertiary = ThemeColors.Dark.onTertiary,
    tertiaryContainer = ThemeColors.Dark.tertiaryContainer,
    onTertiaryContainer = ThemeColors.Dark.onTertiaryContainer,

    surface = ThemeColors.Dark.surface,
    onSurface = ThemeColors.Dark.text,
    background = ThemeColors.Dark.background
)

private val lightColorPalette = lightColorScheme(
    primary = ThemeColors.Light.primary,
    onPrimary = ThemeColors.Light.onPrimary,
    primaryContainer = ThemeColors.Light.primaryContainer,
    onPrimaryContainer = ThemeColors.Light.onPrimaryContainer,

    secondary = ThemeColors.Light.secondary,
    onSecondary = ThemeColors.Light.onSecondary,
    secondaryContainer = ThemeColors.Light.secondaryContainer,
    onSecondaryContainer = ThemeColors.Light.onSecondaryContainer,

    tertiary = ThemeColors.Light.tertiary,
    onTertiary = ThemeColors.Light.onTertiary,
    tertiaryContainer = ThemeColors.Light.tertiaryContainer,
    onTertiaryContainer = ThemeColors.Light.onTertiaryContainer,

    surface = ThemeColors.Light.surface,
    background = ThemeColors.Light.background,
)

@Composable
fun TodoAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) darkColorPalette else lightColorPalette

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}