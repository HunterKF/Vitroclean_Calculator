package com.jaegerapps.vitroclean.android.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp


//Box shadow that is on boxes over surfaces, makes the items a little more visible
//Adds a shadow and clips the item to keep everything standardized.
fun Modifier.boxShadow(): Modifier {

    return this then Modifier
        .shadow(
            elevation = 5.dp,
            shape = RoundedCornerShape(5.dp)
        )
        .clip(RoundedCornerShape(5.dp))
}