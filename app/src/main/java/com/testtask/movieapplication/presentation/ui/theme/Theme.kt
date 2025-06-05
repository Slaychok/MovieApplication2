package com.testtask.movieapplication.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.testtask.movieapplication.R

private val DarkColorScheme = darkColorScheme(

)

private val LightColorScheme = lightColorScheme(

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

//@Composable
//fun MovieApplicationTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val customColorScheme = if (darkTheme) {
//        darkColorScheme(
//            primary = MyYellow,
//            onPrimary = BlackForBackground,
//            background = BlackForBackground,
//            onBackground = Color.White,
//            surface = GrayForContainer,
//            onSurface = Color.White
//        )
//    } else {
//        lightColorScheme(
//            primary = MyYellow,
//            onPrimary = Color.White,
//            background = Color.White,
//            onBackground = Color.Black,
//            surface = Color(0xFFF5F5F5),
//            onSurface = Color.Black
//        )
//    }
//
//    MaterialTheme(
//        colorScheme = customColorScheme,
//        typography = CustomTypography,
//        content = content
//    )
//}

@Composable
fun MovieApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customColorScheme = if (darkTheme) {
        darkColorScheme(
            primary = MyYellow,
            onPrimary = BlackForBackground,
            background = BlackForBackground,
            onBackground = BlackForBackground,
            surface = GrayForContainer,
            onSurface = Color.White
        )
    } else {
        lightColorScheme(
            primary = MyYellow,
            onPrimary = Color.White,
            background = Color.White,
            onBackground = Color.White,
            surface = Color(0xFFF5F5F5),
            onSurface = Color.Black
        )
    }

    MaterialTheme(
        colorScheme = customColorScheme,
        typography = CustomTypography,
        content = content
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