package com.jaegerapps.vitroclean.android.presentation.home_screen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.core.presentation.Routes
import com.jaegerapps.vitroclean.android.presentation.home_screen.components.GuideLinkBox
import com.jaegerapps.vitroclean.android.presentation.home_screen.components.HomeButton
import com.jaegerapps.vitroclean.shared.presentation.home.HomeUiEvent
import com.jaegerapps.vitroclean.shared.presentation.home.HomeUiState
import com.jaegerapps.vitroclean.android.R

@Composable
fun HomeScreen(
    homeState: HomeUiState,
    onUiEvent: (HomeUiEvent) -> Unit,
) {
    val context = LocalContext.current
    if (homeState.showPopUp) {
        /*Alert is happens when user clicks on guide link. Asks if they want to leave the app*/
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
                        Text(stringResource(R.string.no))
                    }
                    Box(
                        modifier = Modifier.clickable {
                            val intent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(homeState.link))

                            onUiEvent(HomeUiEvent.OnAccept { context.startActivity(intent) })
                        }
                    ) {
                        Text(stringResource(R.string.yes))
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
                /*Displays the Vitroclean logo*/
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.6f),
                    painter = painterResource(id = com.jaegerapps.vitroclean.R.drawable.logo_vitroclean),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }
            item {
                /*Navigate to calculator - mode by filter*/
                HomeButton(
                    text = stringResource(R.string.calculate_by_filter),
                    icon = com.jaegerapps.vitroclean.R.drawable.icon_calculator,
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
                /*Navigate to calculator - mode by cubic feet*/
                HomeButton(
                    text = stringResource(R.string.calculate_by_cubic_feet),
                    icon = com.jaegerapps.vitroclean.R.drawable.icon_cube,
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
                /*Navigate to calculator - mode by sand needed*/

                HomeButton(
                    text = stringResource(R.string.calculate_by_sand),
                    icon = com.jaegerapps.vitroclean.R.drawable.icon_sand,
                    onClick = { onUiEvent(HomeUiEvent.OnNavigate(Routes.CALCULATOR, Routes.SAND)) },
                    contentDescription = stringResource(R.string.calculate_by_sand)
                )
            }
            item {
                /*Navigate to FAQs screen*/

                HomeButton(
                    text = stringResource(R.string.faqs),
                    icon = com.jaegerapps.vitroclean.R.drawable.icon_question,
                    onClick = { onUiEvent(HomeUiEvent.OnNavigate(Routes.FAQS, null)) },
                    contentDescription = stringResource(R.string.faqs)
                )
            }

            item {
                /*Navigate to Contact Us screen*/

                HomeButton(
                    text = stringResource(R.string.contact_us),
                    icon = com.jaegerapps.vitroclean.R.drawable.icon_message,
                    onClick = { onUiEvent(HomeUiEvent.OnNavigate(Routes.CONTACT_US, null)) },
                    contentDescription = stringResource(R.string.contact_us)
                )
            }
            item {
                /*On click will open the toggle the alert pop up.
                * Used to navigate to Vitroclean website*/
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