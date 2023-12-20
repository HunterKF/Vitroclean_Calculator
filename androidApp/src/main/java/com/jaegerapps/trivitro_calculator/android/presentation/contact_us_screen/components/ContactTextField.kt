package com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen.components

import android.graphics.Paint.Align
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.lerp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaegerapps.trivitro_calculator.android.R
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.android.presentation.components.boxShadow
import com.jaegerapps.trivitro_calculator.shared.presentation.Colors

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
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Email
    ),
    error: Boolean = false
) {


    Box(
        modifier = modifier
            .boxShadow()
            .background(MaterialTheme.colors.surface),

        ) {
        TextField(
            modifier = Modifier
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
                    style = if (text == "") MaterialTheme.typography.body1.copy(
                        color = Color.Gray
                    ) else MaterialTheme.typography.body2.copy(
                        color = Color.Gray
                    )
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ContactTextField(
                text = "3232",
                extraContent = { /*TODO*/ },
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
                extraContent = { /*TODO*/ },
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
                text = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin sit amet massa rutrum erat molestie euismod. Nullam porta, risus non interdum interdum, nulla arcu molestie elit, ut feugiat nibh odio eget odio. In vestibulum nunc vitae ex varius pharetra. Aenean mattis mauris ut felis pharetra, eget interdum mi pretium. Proin est ligula, commodo eget quam in, lacinia euismod nisi. Donec eleifend justo id nisl pretium suscipit. Nullam quis arcu nulla. Fusce fermentum arcu et mauris eleifend tincidunt. Ut viverra eleifend nunc sed ultrices. Cras imperdiet bibendum lectus vel sollicitudin. Sed ut augue vehicula, euismod nisi sed, interdum ex. Aliquam rhoncus congue tempus. Morbi vitae diam vel est rhoncus fermentum. Vivamus ut vehicula felis, at malesuada enim. Suspendisse potenti.\"",
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
