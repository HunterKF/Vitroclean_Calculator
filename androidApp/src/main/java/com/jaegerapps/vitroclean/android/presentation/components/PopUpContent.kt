package com.jaegerapps.vitroclean.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.vitroclean.android.TrivitroTheme

@Composable
fun PopUpContent(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    buttonContent: @Composable () -> Unit,
) {
    Box(
        modifier
            .boxShadow()
            .background(MaterialTheme.colors.surface)
    ) {
        Column {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h2,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                )
            }
            Column {
                Spacer(Modifier.height(8.dp))
                Divider(Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                buttonContent()
            }
        }
    }
}


@Preview
@Composable
fun PopUpPreview() {
    TrivitroTheme {
        Column() {
            PopUpContent(
                title = "Navigating out of app",
                text = "You are about to leave the app to go to to the Trivitro website. Do you want to proceed?"
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Cancel,
                        contentDescription = "Cancel",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = "Accept",
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
            PopUpContent(
                title = "There was a problem...",
                text = "Couldn't connect to the network. Please try again later."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "OK", style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}