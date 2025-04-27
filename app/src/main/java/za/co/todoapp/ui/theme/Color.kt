package za.co.todoapp.ui.theme

import androidx.compose.ui.graphics.Color

val LightModeTertiary = Color(0xFF303F9F)
val LightModeSecondary = Color(0xFFEF4B4C)
val LightModePrimary = Color(0xFF3F51B5)
val LightModeSurface = Color(0xFFE9F0F4)

val DarkModeTertiary = Color(0xFF212121)
val DarkModeSecondary = Color(0xFFEF4B4C)
val DarkModePrimary = Color(0xFF3F51B5)
val DarkModeSurface = Color(0xFF757575)

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val text: Color
)  {
    data object Dark: ThemeColors(
        primary = DarkModePrimary,
        onPrimary = Color.White,
        primaryContainer = DarkModePrimary,
        onPrimaryContainer = Color.White,

        secondary = DarkModeSecondary,
        onSecondary = Color.White,
        secondaryContainer = DarkModeSecondary,
        onSecondaryContainer = Color.White,

        tertiary = DarkModeTertiary,
        onTertiary = Color.White,
        tertiaryContainer = DarkModeTertiary,
        onTertiaryContainer = Color.White,

        text = Color.White,
        background = Color.Black,
        surface = DarkModeSurface
    )
    data object Light: ThemeColors(
        primary = LightModePrimary,
        onPrimary = Color.White,
        primaryContainer = LightModePrimary,
        onPrimaryContainer = Color.White,

        secondary = LightModeSecondary,
        onSecondary = Color.White,
        secondaryContainer = LightModeSecondary,
        onSecondaryContainer = Color.White,

        tertiary = LightModeTertiary,
        onTertiary = Color.White,
        tertiaryContainer = LightModeTertiary,
        onTertiaryContainer = Color.White,

        text = Color.Black,
        background = Color.White,
        surface = LightModeSurface,
    )
}