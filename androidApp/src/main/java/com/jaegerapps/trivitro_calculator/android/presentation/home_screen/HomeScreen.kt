package com.jaegerapps.trivitro_calculator.android.presentation.home_screen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import com.jaegerapps.trivitro_calculator.android.R
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.android.core.presentation.Routes
import com.jaegerapps.trivitro_calculator.android.presentation.components.PopUpContent
import com.jaegerapps.trivitro_calculator.android.presentation.home_screen.components.GuideLinkBox
import com.jaegerapps.trivitro_calculator.android.presentation.home_screen.components.HomeButton
import com.jaegerapps.trivitro_calculator.shared.presentation.home.HomeUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.home.HomeUiState

@Composable
fun HomeScreen(
    homeState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit,
) {
    val context = LocalContext.current
    if (homeState.showPopUp) {
        AlertDialog(onDismissRequest = { onUiEvent(HomeUiEvent.OnDismiss) },
            title = {
                Text(stringResource(R.string.title_navigate_out_of_app))
            },
            text = {
                Text(stringResource(R.string.explanation_navigate_out_of_app))
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
                        modifier = Modifier.clickable {
                            onUiEvent(HomeUiEvent.OnDismiss)
                        }
                    ) {
                        Text("No")
                    }
                    Box(
                        modifier = Modifier.clickable {
                            val intent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(homeState.link))

                            onUiEvent(HomeUiEvent.OnAccept { context.startActivity(intent) })
                        }
                    ) {
                        Text("Yes")
                    }

                }
            }
        )
        Box(
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize()
                .background(
                    Color.Black.copy(
                        alpha = 0.4f
                    )
                )
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = MaterialTheme.colors.surface
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 12.dp)
                .scrollable(rememberScrollState(), Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
            }
            item {

                HomeButton(
                    text = stringResource(R.string.calculate_by_filter),
                    icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_calculator,
                    onClick = {
                        onUiEvent(
                            HomeUiEvent.OnNavigate(
                                Routes.CALCULATOR,
                                Routes.FILTER
                            )
                        )
                    },
                    contentDescription = stringResource(R.string.calculate_by_filter)
                )
            }
            item {

                HomeButton(
                    text = stringResource(R.string.calculate_by_cubic_feet),
                    icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_cube,
                    onClick = {
                        onUiEvent(
                            HomeUiEvent.OnNavigate(
                                Routes.CALCULATOR,
                                Routes.CUBIC_FEET
                            )
                        )
                    },
                    contentDescription = stringResource(R.string.calculate_by_cubic_feet)
                )
            }
            item {

                HomeButton(
                    text = stringResource(R.string.calculate_by_sand),
                    icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_sand,
                    onClick = { onUiEvent(HomeUiEvent.OnNavigate(Routes.CALCULATOR, Routes.SAND)) },
                    contentDescription = stringResource(R.string.calculate_by_sand)
                )
            }
            item {
                HomeButton(
                    text = stringResource(R.string.faqs),
                    icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_question,
                    onClick = { onUiEvent(HomeUiEvent.OnNavigate(Routes.FAQS, null)) },
                    contentDescription = stringResource(R.string.faqs)
                )
            }

            item {
                HomeButton(
                    text = stringResource(R.string.contact_us),
                    icon = com.jaegerapps.trivitro_calculator.R.drawable.icon_message,
                    onClick = { onUiEvent(HomeUiEvent.OnNavigate(Routes.CONTACT_US, null)) },
                    contentDescription = stringResource(R.string.contact_us)
                )
            }
            item {

                GuideLinkBox(onClick = {
                    onUiEvent(HomeUiEvent.OnClick)
                })
            }
            item {
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    TrivitroTheme() {
        val state = HomeUiState(showPopUp = false)
        HomeScreen(homeState = state) {

        }
    }
}