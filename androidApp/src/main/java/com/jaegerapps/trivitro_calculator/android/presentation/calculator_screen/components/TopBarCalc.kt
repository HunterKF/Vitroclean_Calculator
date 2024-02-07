package com.jaegerapps.trivitro_calculator.android.presentation.calculator_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.trivitro_calculator.android.R
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.shared.domain.models.PoolFilter

@Composable
fun TopBarCalc(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(bottom = 20.dp, top = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(Modifier.fillMaxWidth()) {
            IconButton(onClick = { onBackClick() }, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(R.string.content_desc_back_button),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
        content()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun TopBarCalc() {
    val exampleList = listOf(
        PoolFilter("Pentair", "TR40", 175, 140, 0, 0.0, 3.0),
        PoolFilter("Pentair", "TR50", 225, 180, 0, 0.0, 4.0),
        PoolFilter("Pentair", "TR60", 325, 260, 182, 78.0, 4.0),
        PoolFilter("Pentair", "TR60 ClearPro", 325, 260, 182, 78.0, 4.0),
        PoolFilter("Pentair", "TR100", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR100HD", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR100C-3", 600, 480, 336, 144.0, 7.0),
        PoolFilter("Pentair", "TR-140", 925, 740, 518, 222.0, 11.0),
        PoolFilter("Pentair", "TR140C-3", 925, 740, 518, 222.0, 11.0),
        PoolFilter("Hayward", "S144T", 50, 40, 40, 0.0, 1.0),
        PoolFilter("Hayward", "S160T", 150, 120, 120, 0.0, 3.0),
        PoolFilter("Waterway Carefree Top-Mount", "FS02629-B (26\")", 300, 240, 168, 72.0, 4.0)
    )


    TrivitroTheme {
        Column {
            val controller = LocalSoftwareKeyboardController.current
            TopBarCalc(onBackClick = { /*TODO*/ }) {
                NumberDisplay(
                    text = "0", subtext = "sand needed",
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(0.9f),
                    controller = controller
                )

            }
            Spacer(modifier = Modifier.height(12.dp))
            TopBarCalc(onBackClick = { /*TODO*/ }) {
                DropDownBox(
                    list = exampleList.map { it.model },
                    currentItem = "TR40",
                    defaultText = "Select model",
                    onEvent = {},
                    onToggleEvent = {},
                    modifier = Modifier.fillMaxWidth(0.9f),
                    showDropDown = true
                )

            }
            Spacer(modifier = Modifier.height(12.dp))
            TopBarCalc(onBackClick = { /*TODO*/ }) {
                DropDownBox(
                    list = exampleList.map { it.model },
                    currentItem = null,
                    defaultText = "Select model",
                    onEvent = {},
                    onToggleEvent = {},
                    modifier = Modifier.fillMaxWidth(0.9f),
                    showDropDown = false
                )
            }
        }


    }
}