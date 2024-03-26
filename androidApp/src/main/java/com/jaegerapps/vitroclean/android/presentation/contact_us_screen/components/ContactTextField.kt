package com.jaegerapps.vitroclean.android.presentation.contact_us_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.vitroclean.android.TrivitroTheme
import com.jaegerapps.vitroclean.android.presentation.components.boxShadow

@Composable
fun ContactTextField(
    modifier: Modifier = Modifier,
    text: String,
    extraContent: @Composable () -> Unit,
    defaultText: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Sentences,
        autoCorrect = true,
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email
    ),
    error: Boolean = false
) {


    Box(
        modifier = modifier
            .boxShadow()
            .background(MaterialTheme.colors.background),

        ) {
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = text,
            isError = error,
            onValueChange = { newValue -> onValueChange(newValue) },
            textStyle = MaterialTheme.typography.body1.copy(
                color = MaterialTheme.colors.primary,
                fontSize = 16.sp,
                textIndent = TextIndent(firstLine = 8.sp)
            ),
            label = {
                Text(
                    defaultText,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground
                )

            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primary,
                focusedIndicatorColor =  MaterialTheme.colors.secondaryVariant,
                unfocusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent
            )
        )
        Box(
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.BottomEnd)
        ) {
            extraContent()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContactTextFieldPreview() {
    TrivitroTheme {
        var state by remember { mutableStateOf("")}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ContactTextField(
                text = "3232",
                extraContent = {  },
                defaultText = "E-Mail",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {}
                ),
                singleLine = true
            )
            ContactTextField(
                text = "",
                error = true,
                extraContent = {  },
                defaultText = "Name",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {}
                ),
                singleLine = true
            )
            ContactTextField(
                text = "12212122",
                extraContent = {
                    Box(modifier = Modifier.align(Alignment.End))
                },
                defaultText = "Subject",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {}
                ),
                singleLine = true
            )
            ContactTextField(
                modifier = Modifier.height(150.dp),
                text = state,
                extraContent = { Text("400", color = Color.Gray) },
                defaultText = "Email Content",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onDone = {}
                ),
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContactTextFieldPreview_DARK() {
    TrivitroTheme {
        var state by remember { mutableStateOf("")}
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ContactTextField(
                text = "3232",
                extraContent = {  },
                defaultText = "E-Mail",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {}
                ),
                singleLine = true
            )
            ContactTextField(
                text = "",
                error = true,
                extraContent = {  },
                defaultText = "Name",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {}
                ),
                singleLine = true
            )
            ContactTextField(
                text = "12212122",
                extraContent = {
                    Box(modifier = Modifier.align(Alignment.End))
                },
                defaultText = "Subject",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onNext = {}
                ),
                singleLine = true
            )
            ContactTextField(
                modifier = Modifier.height(150.dp),
                text = state,
                extraContent = { Text("400", color = Color.Gray) },
                defaultText = "Email Content",
                onValueChange = {},
                keyboardActions = KeyboardActions(
                    onDone = {}
                ),
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}
