package com.jaegerapps.vitroclean.android.presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.vitroclean.android.R
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.presentation.components.boxShadow
import com.jaegerapps.vitroclean.android.presentation.contact_us_screen.components.ActionButton


/*Onboarding screen to introduce the user to the app*/
@Composable
fun OnboardingScreen(
    onClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 12.dp)
            .verticalScroll(scrollState)

    ) {
        // Onboarding image
        Spacer(modifier = Modifier.height(36.dp))
        Column(
            modifier = Modifier
//                .weight(1f)
                .fillMaxWidth()
                .boxShadow()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(36.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = com.jaegerapps.vitroclean.R.drawable.onboarding_screen),
                    contentDescription = "Onboarding Image",
                    modifier = Modifier
//                        .weight(1f)
                        .fillMaxWidth(0.6f)
                        .padding(12.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
//            Spacer(modifier = Modifier.height(36.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp, start = 24.dp, end = 24.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.onboarding_title),
                    style = MaterialTheme.typography.h1.copy(
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = stringResource(R.string.onboarding_message),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier
            .weight(1f)
        )

        // Button
        ActionButton(
            modifier = Modifier.padding(top = 24.dp),
            onClick = { onClick() },
            isEnabled = true,
            text = stringResource(R.string.onboarding_button_prompt)
        )
        Spacer(modifier = Modifier.height(36.dp))


    }
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    TrivitroTheme {
        OnboardingScreen() {}
    }
}