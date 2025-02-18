package com.jaegerapps.vitroclean.android.presentation.loading_screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jaegerapps.vitroclean.android.R
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.shared.domain.NetworkError
import com.jaegerapps.vitroclean.shared.presentation.SharedUiState
import kotlinx.coroutines.delay

/*Loading screen. Displays dot to show that the app is loading
* During this screen, 3 things happen
* 1) Faqs are gotten from remote dn
* 2) Filters are gotten from remote db
* 3) Checks if onboarding should be shown*/
@Composable
fun LoadingScreen(
    isLoading: Boolean,
    error: NetworkError? = null,
    onRetry: () -> Unit,
) {
    var message: String? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    LaunchedEffect(key1 = error) {

        if (error != null) {
           message =  when (error) {
                NetworkError.SERVICE_UNAVAILABLE -> context.getString(R.string.error_message_service_unavailable)
                NetworkError.CLIENT_ERROR -> context.getString(R.string.error_message_client_error)
                NetworkError.SERVER_ERROR -> context.getString(R.string.error_message_server_error)
                NetworkError.UNKNOWN_ERROR -> context.getString(R.string.error_message_unknown_error)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading && error == null) {
            LoadingContent()
        } else if (error != null) {
            AlertDialog(
                onDismissRequest = { },
                title = {
                    Text(stringResource(R.string.title_error))
                },
                text = {
                    message?.let { Text(it) }
                },
                shape = RoundedCornerShape(5.dp),
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onRetry()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Retry")
                        }

                    }
                }
            )
        }
    }
}


@Composable
fun LoadingContent() {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth(0.7f)) {
            Image(
                painter = painterResource(id = com.jaegerapps.vitroclean.R.drawable.logo_vitroclean),
                contentDescription = stringResource(R.string.content_desc_vitroclean_logo),
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        LoadingDots()
    }
}

@Composable
fun LoadingDots(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = MaterialTheme.colors.primary,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp,
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEach { value ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = circleColor,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Preview
@Composable
fun LoadingDotsPreview() {
    TrivitroTheme() {
        LoadingDots()
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    TrivitroTheme() {
        val state = SharedUiState(
            isLoading = false,
            error = NetworkError.SERVER_ERROR
        )
        LoadingScreen(state.isLoading, error = state.error) { }
    }
}