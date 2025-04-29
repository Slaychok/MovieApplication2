package com.testtask.movieapplication.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.testtask.movieapplication.R

// Цвета для светлой темы (можно задать свои или использовать стандартные)
val LightBackground = Color(0xFFFFFFFF)
val LightTextPrimary = Color(0xFF000000)


private val DarkColorScheme = darkColorScheme(
    primary = MyYellow,
    onPrimary = Color.Black,
    secondary = GrayForFont,
    onSecondary = Color.White,
    tertiary = GrayForIcons,
    background = BlackForBackground,
    surface = GrayForContainer,
    onBackground = Color.White,
    onSurface = Color.White.copy(alpha = 0.8f)
)

private val LightColorScheme = lightColorScheme(
    primary = MyYellow,
    onPrimary = Color.Black,
    secondary = GrayForFont,
    onSecondary = Color.Black,
    tertiary = GrayForIcons,
    background = LightBackground,
    surface = Color.White,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary.copy(alpha = 0.8f)
)

@Composable
fun MovieApplicationTheme(
    darkTheme: Boolean = AppThemeSettings.isDarkTheme,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CustomTypography,
        content = content
    )
}

object AppThemeSettings {
    var isDarkTheme by mutableStateOf(false)
}

@Composable
fun ThemeSwitcher() {
    val isDarkTheme = AppThemeSettings.isDarkTheme

    Switch(
        checked = isDarkTheme,
        onCheckedChange = { newValue ->
            AppThemeSettings.isDarkTheme = newValue
        },
        colors = SwitchDefaults.colors(
            checkedThumbColor = MyYellow,
            uncheckedThumbColor = MyYellow
        )
    )
}

val SkModernist = FontFamily(
    Font(R.font.skmodernist, FontWeight.Bold),
)

val CustomTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = SkModernist,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = SkModernist,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
)