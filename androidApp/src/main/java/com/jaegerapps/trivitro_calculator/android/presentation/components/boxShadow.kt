package com.jaegerapps.trivitro_calculator.android.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

fun Modifier.boxShadow(): Modifier = composed {
    Modifier
        .shadow(
            elevation = 5.dp,
            shape = RoundedCornerShape(5.dp)
        )
        .clip(RoundedCornerShape(5.dp))
}