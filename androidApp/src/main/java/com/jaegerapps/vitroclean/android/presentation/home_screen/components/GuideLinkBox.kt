package com.jaegerapps.vitroclean.android.presentation.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.presentation.components.boxShadow
import com.jaegerapps.vitroclean.shared.presentation.home.HomeUiEvent
import com.jaegerapps.vitroclean.android.R

@Composable
fun GuideLinkBox(
    modifier: Modifier = Modifier,
    onClick: (HomeUiEvent) -> Unit,
) {
    Box(modifier = modifier
        .clickable { onClick(HomeUiEvent.OnClick) }
        .boxShadow()) {

        Box(
            modifier = Modifier.aspectRatio(1.8f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = com.jaegerapps.vitroclean.R.drawable.photo_pool),
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
        Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
            GuideLinkBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                onClick = {}
            )
        }
    }

}