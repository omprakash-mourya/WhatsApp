package com.opdroid.whatsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.opdroid.whatsapp.theme.ThemeManager

private val DarkColorScheme = darkColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = DarkGray,
    onSecondary = Gray,
    tertiary = Green,
    onTertiary = Blue,
    error = Red,
    background = Black,
    onBackground = DarkGray,
    inversePrimary = LightGray
)

private val LightColorScheme = lightColorScheme(
    primary = White,
    onPrimary = Black,
    secondary = LightGray,
    onSecondary = Gray,
    tertiary = Green,
    onTertiary = Blue,
    error = Red,
    background = White,
    onBackground = LightGray,
    inversePrimary = DarkGray


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun WhatsAppTheme(
    systemDarkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    /*val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }*/

    val darkTheme = ThemeManager.isDarkTheme || systemDarkTheme
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography
    ){
        val systemUiController = rememberSystemUiController()
//        val useDarkIcons = MaterialTheme.colorScheme.equals(LightColorScheme)
        val useDarkIcons = MaterialTheme.colorScheme.background == LightColorScheme.background
        val primaryColorAsInt = MaterialTheme.colorScheme.primary.toArgb()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color(primaryColorAsInt),
                darkIcons = useDarkIcons
            )
        }

        content()
    }
}