package com.jaegerapps.vitroclean.android.presentation.calculator_screen.components

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.shared.domain.models.PoolFilter

/*Used for calculator select filter mode
* Displays a list, the selected item is highlighted at the top
* The default text is a prompt for the user to select something if nothing is selected*/
@Composable
fun DropDownBox(
    list: List<String>? = null,
    currentItem: String?,
    showDropDown: Boolean,
    defaultText: String,
    modifier: Modifier = Modifier,
    onEvent: (String) -> Unit,
    onToggleEvent: () -> Unit
) {
    /*This will rotate the drop down arrows*/
    val animation: Float by animateFloatAsState(if (showDropDown) 180f else 0f)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.surface)
    ) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            ) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 150.dp)
                ) {
                    item {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onToggleEvent()
                                }
                                .padding(start = 12.dp, top = 8.dp, bottom = 8.dp)
                                ,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = currentItem ?: defaultText,
                                style = if (currentItem == null) MaterialTheme.typography.body1 else MaterialTheme.typography.h3,
                                color = if (currentItem != null) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface,
                                fontSize = 16.sp
                            )
                            Icon(
                                Icons.Rounded.ArrowDropDown,
                                modifier = Modifier.rotate(animation),
                                contentDescription = null
                            )
                        }
                        if (showDropDown) {
                            Divider(Modifier.fillMaxWidth())
                        }
                    }
                    if (showDropDown) {
                        list?.let { text ->
                            items(text) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .clickable { onEvent(it) }
                                        .padding(start = 12.dp, top = 8.dp, bottom = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.body1.copy(
                                            color = MaterialTheme.colors.onSurface
                                        ),
                                        fontSize = 16.sp
                                    )
                                }
                                if (list.last() != it) {
                                    Divider(Modifier.fillMaxWidth())
                                }
                            }
                        }
                    }

                }
            }
        }

    }
}

@Preview
@Composable
fun DropDownBoxPreview() {
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

            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = null,
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = null,
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = "TR40",
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = "TR40",
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = false
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DropDownBoxPreviewDark() {
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

            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = null,
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = null,
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = "TR40",
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            DropDownBox(
                list = exampleList.map { it.model },
                currentItem = "TR40",
                defaultText = "Select model",
                onEvent = {},
                onToggleEvent = {},
                modifier = Modifier.fillMaxWidth(),
                showDropDown = false
            )
        }
    }
}

