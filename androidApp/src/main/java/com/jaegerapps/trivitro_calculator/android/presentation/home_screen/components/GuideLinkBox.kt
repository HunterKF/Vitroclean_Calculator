package com.jaegerapps.trivitro_calculator.android.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.trivitro_calculator.android.R
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.android.presentation.components.boxShadow
import com.jaegerapps.trivitro_calculator.shared.presentation.home.HomeUiEvent

@Composable
fun GuideLinkBox(
    modifier: Modifier = Modifier,
    onClick: (HomeUiEvent) -> Unit,
) {
    Box(modifier = modifier
        .clickable { onClick(HomeUiEvent.OnClick) }
        .boxShadow()) {

        Box(
            modifier = Modifier.aspectRatio(1.8f)
        ) {
            Image(
                painter = painterResource(id = com.jaegerapps.trivitro_calculator.R.drawable.photo_pool),
                contentDescription = "pool",
                contentScale = ContentScale.Crop
            )
            BlackScrim(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .align(Alignment.BottomCenter)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.prompt_link_to_site),
                    style = MaterialTheme.typography.h3,
                    color = Color.White
                )
            }

        }
    }
}

@Preview
@Composable
fun GuideLinkBoxPreview() {
    TrivitroTheme() {
        GuideLinkBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            onClick = {}
        )
    }

}