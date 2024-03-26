package com.jaegerapps.vitroclean.android.presentation.faqs_screen.components

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.presentation.components.boxShadow

@Composable
fun FaqContainer(
    modifier: Modifier = Modifier,
    title: String,
    contentText: String,
    isOpen: Boolean,
    onClick: () -> Unit,
) {
    val fgColor: Color by animateColorAsState(if (isOpen) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary)
    val bgColor: Color by animateColorAsState(if (!isOpen) MaterialTheme.colors.background else MaterialTheme.colors.primary)
    val animateFloat: Float by animateFloatAsState(if (isOpen) 180f else 0f)
    Column(
        modifier = modifier
            .boxShadow()
            .background(bgColor)
            .clickable { onClick() }
            .animateContentSize(
                animationSpec = tween(350)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h3.copy(
                    color = fgColor
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(
                modifier = Modifier.rotate(animateFloat),
                imageVector = Icons.Rounded.ArrowDropDown,
                tint = fgColor,
                contentDescription = "Expand FAQ: $title"
            )
        }
        if (isOpen) {
            Column(
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 0.dp)
            ) {
                Text(
                    text = contentText,
                    style = MaterialTheme.typography.h3.copy(
                        color = MaterialTheme.colors.onPrimary,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun FaqContainerPreview() {
    TrivitroTheme {
        Column {
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = false
            ) {

            }
            Spacer(modifier = Modifier.height(12.dp))
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = true
            ) {

            }
        }

    }
}
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FaqContainerPreview_DARK() {
    TrivitroTheme {
        Column {
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = false
            ) {

            }
            Spacer(modifier = Modifier.height(12.dp))
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = true
            ) {

            }
        }

    }
}

@Preview
@Composable
fun FaqContainerListPreview() {
    TrivitroTheme {
        var state by remember { mutableStateOf(false) }
        Column {
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = state
            ) {
                state = !state
            }
            Spacer(modifier = Modifier.height(20.dp))
            FaqContainer(
                title = "How do I install a filter?2134122354423534534523453245345",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = !state
            ) {
                state = !state

            }
            Spacer(modifier = Modifier.height(20.dp))
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = !state
            ) {
                state = !state

            }
            Spacer(modifier = Modifier.height(20.dp))
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = !state
            ) {
                state = !state

            }
            Spacer(modifier = Modifier.height(20.dp))
            FaqContainer(
                title = "How do I install a filter?",
                contentText = "Lorem ipsum dolor sit amet consectetur. Ut aenean dignissim mauris gravida volutpat etiam. Purus viverra ut in aliquam egestas enim eget in. Diam faucibus diam ullamcorper ac. Sed elit semper vitae tristique velit leo pretium commodo. Sit viverra dui turpis sit ac diam sed pellentesque. Enim ut ut",
                isOpen = !state
            ) {
                state = !state

            }
        }

    }
}