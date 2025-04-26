package za.co.todoapp.ui.theme

import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF38619B)
val Red = Color(0xFFEF4B4C)
val NavyBlue = Color(0xFF43506C)
val LightGrayishBlue = Color(0xFFE9F0F4)

val DarkModeBlack = Color(0xFF000000)
val DarkModeDarkGray = Color(0xFF222222)
val DarkModeDarkGreen = Color(0xFF169976)

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
        primary = DarkModeDarkGreen,
        onPrimary = Color.White,
        primaryContainer = DarkModeDarkGreen,
        onPrimaryContainer = Color.White,

        secondary = Red,
        onSecondary = Color.White,
        secondaryContainer = Red,
        onSecondaryContainer = Color.White,

        tertiary = DarkModeBlack,
        onTertiary = Color.White,
        tertiaryContainer = DarkModeBlack,
        onTertiaryContainer = Color.White,

        text = Color.White,
        background = Color.Black,
        surface = DarkModeDarkGray
    )
    data object Light: ThemeColors(
        primary = Blue,
        onPrimary = Color.White,
        primaryContainer = Blue,
        onPrimaryContainer = Color.White,

        secondary = Red,
        onSecondary = Color.White,
        secondaryContainer = Red,
        onSecondaryContainer = Color.White,

        tertiary = NavyBlue,
        onTertiary = Color.White,
        tertiaryContainer = NavyBlue,
        onTertiaryContainer = Color.White,

        text = Color.Black,
        background = Color.White,
        surface = LightGrayishBlue,
    )
}