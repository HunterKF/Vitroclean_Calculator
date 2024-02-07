package com.jaegerapps.trivitro_calculator.android.core.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import com.jaegerapps.trivitro_calculator.shared.presentation.Colors

val TrivitroBlue = Color(Colors.TrivitroBlue)
val TrivitroBlueDarkMode = Color(Colors.TrivitroBlueDarkMode)
val SeaGreen = Color(Colors.SecondarySeaGreen)
val LightGray = Color(Colors.LightGray)
val TextBlack = Color(Colors.TextBlack)
val DarkGrey = Color(Colors.DarkGray)
val Gray = Color(Colors.Gray)
/*TODO - Change iOS onSecondary color*/
val lightColors = lightColors(
    primary = TrivitroBlue,
    background = Color.White,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = LightGray,
    onSurface = TextBlack,
    onSecondary = Color.Black
)

val darkColors = darkColors(
    primary = TrivitroBlueDarkMode,
    background = Gray,
    onPrimary = Color.White,
    onBackground = Color.White,
    surface = DarkGrey,
    onSurface = Color.White,
    onSecondary = Color.Black

)