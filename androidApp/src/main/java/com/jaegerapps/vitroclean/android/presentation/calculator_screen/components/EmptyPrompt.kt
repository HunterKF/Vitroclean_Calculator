package com.jaegerapps.vitroclean.android.presentation.calculator_screen.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.vitroclean.android.TrivitroTheme

/*If there is nothing to display, aka the user just navigated to the screen,
* we display this prompt. It takes in an icon and text.
* Ex. icon = icon_calculator and text "find your filter"*/
@Composable
fun EmptyPrompt(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(140.dp)
                .rotate(20f),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface.copy(
                alpha = 0.1f
            )
        )
        Text(
            text = text,
            style = MaterialTheme.typography.h1.copy(
                textAlign = TextAlign.Center
            ),
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Preview
@Composable
private fun EmptyPromptPreview() {
    TrivitroTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EmptyPrompt(
                text = "Find your filter",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_calculator
            )

        }
    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun EmptyPromptPreview_DARK() {
    TrivitroTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EmptyPrompt(
                text = "Find your filter",
                icon = com.jaegerapps.vitroclean.R.drawable.icon_calculator
            )

        }
    }
}