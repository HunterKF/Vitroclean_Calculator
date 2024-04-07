package com.jaegerapps.vitroclean.android.presentation.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.vitroclean.android.R
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.presentation.components.boxShadow
import com.jaegerapps.vitroclean.android.presentation.contact_us_screen.components.ActionButton

@Composable
fun OnboardingScreen(
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 12.dp, vertical = 24.dp)
    ) {
        // Onboarding image
        Box( modifier = Modifier
            .fillMaxWidth()
            .boxShadow()
            .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center) {

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){

                Spacer(modifier = Modifier.height(36.dp))
                Image(
                    painter = painterResource(id = com.jaegerapps.vitroclean.R.drawable.onboarding_screen),
                    contentDescription = "Onboarding Image",
                    modifier = Modifier
//                        .weight(1f)
                        .fillMaxWidth(0.6f)
                        .padding(12.dp),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(36.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Welcome",
                        style = MaterialTheme.typography.h1.copy(
                            color = MaterialTheme.colors.primary
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Welcome to Vitroclean's calculator, your seamless solution for precisely determining the right amount of glass filter media for your pool, tailored by filter model, sand requirements, or cubic feet.",
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Button
        ActionButton(
            onClick = { onClick() },
            isEnabled = true,
            text = stringResource(R.string.get_started)
        )

    }
}

@Preview
@Composable
fun PreviewOnboardingScreen() {
    TrivitroTheme {
        OnboardingScreen() {}
    }
}