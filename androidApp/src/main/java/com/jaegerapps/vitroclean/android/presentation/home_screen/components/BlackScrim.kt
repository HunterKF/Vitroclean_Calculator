package com.jaegerapps.vitroclean.android.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun BlackScrim(modifier: Modifier) {
    //This is to make the bottom of the photo cards a little more readable
    Box(
        modifier = modifier.background(
            Brush.verticalGradient(
                listOf(Color.Transparent, Color(0x99000000))
            )
        )
    ) {

    }
}