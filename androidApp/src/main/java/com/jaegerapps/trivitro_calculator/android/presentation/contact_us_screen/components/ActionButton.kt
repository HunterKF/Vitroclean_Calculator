package com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.android.presentation.components.boxShadow

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean,
    inactiveColor: Color = Color.Gray,
    activeColor: Color = MaterialTheme.colors.primary,
    textColor: Color = MaterialTheme.colors.onPrimary,
    onClick: () -> Unit,
) {
    val colorBgAni by animateColorAsState(
        if (isEnabled) activeColor else inactiveColor,
        label = "colorAni"
    )
    val colorTextAni by animateColorAsState(
        if (isEnabled) textColor else Color.White.copy(
            alpha = 0.7f
        ),
        label = "colorAni"
    )

    Button(
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorBgAni,
            disabledBackgroundColor = inactiveColor
        ),
        enabled = isEnabled,
        onClick = { onClick() },

        ) {
        Box(
            modifier = Modifier.padding(4.dp)
        ) {

            Text(
                text = text,
                color = colorTextAni,
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@Preview
@Composable
fun ActionButtonPreview() {
    TrivitroTheme {
        Column {
            ActionButton(text = "Send", isEnabled = false) {

            }
            Spacer(modifier = Modifier.height(12.dp))
            ActionButton(text = "Send", isEnabled = true) {

            }
        }

    }
}