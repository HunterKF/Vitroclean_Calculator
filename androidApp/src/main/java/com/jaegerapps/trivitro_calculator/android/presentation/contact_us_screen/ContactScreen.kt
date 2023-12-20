package com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaegerapps.trivitro_calculator.android.R
import com.jaegerapps.trivitro_calculator.android.TrivitroTheme
import com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen.components.ActionButton
import com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen.components.ContactTextField
import com.jaegerapps.trivitro_calculator.android.presentation.contact_us_screen.components.sendMail
import com.jaegerapps.trivitro_calculator.shared.presentation.contact_us.ContactError
import com.jaegerapps.trivitro_calculator.shared.presentation.contact_us.ContactState
import com.jaegerapps.trivitro_calculator.shared.presentation.contact_us.ContactUiEvent
import com.jaegerapps.trivitro_calculator.shared.presentation.contact_us.ContactViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContactScreen(
    state: ContactState,
    onEvent: (ContactUiEvent) -> Unit,
    onBack: () -> Unit,
) {
    /*Contains everything for the contact screen. Simple textfields will go into an email intent*/
    val focusManager = LocalFocusManager.current
    val controller = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        /*Checking if there is an error from the viewmodel then it will display a snackbar*/
        if (state.error != null) {
            val message =
                when (state.error) {
                    ContactError.INVALID_EMAIL -> {
                        "Please check your email"
                    }

                    ContactError.DATA_MISSING -> {
                        "Fill out all of the fields"
                    }
                    ContactError.NO_EMAIL_APP -> {
                        "Couldn't find email app. Please go to website to contact us."
                    }
                    else -> {
                        "An unexpected error occurred, try again later."
                    }
                }

            snackbarHostState.showSnackbar(message)
            onEvent(ContactUiEvent.ClearToast)
        }
    }
    /*Added scaffold to display the snackbar*/
    Scaffold(

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->

        Surface(
            modifier = Modifier.padding(padding),
            color = MaterialTheme.colors.background
        ) {
            /*Lazy column is used because it scales the content nicely and the user can scroll even when keyboard is open*/
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    /*Title and back button*/
                    Column {
                        Box(Modifier.fillMaxWidth()) {
                            IconButton(
                                onClick = { onBack() },
                                modifier = Modifier.align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.ArrowBack,
                                    contentDescription = stringResource(R.string.content_desc_back_button)
                                )
                            }
                        }
                        Text(
                            text = "Contact Us",
                            style = MaterialTheme.typography.h1.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )
                    }

                }
                item {
                    /*TextFields for entry*/
                    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                        /*This is for the email field, but since we are doing the email from a different app, we don't need this.
                        ContactTextField(
                            modifier = Modifier.onFocusChanged {
                                if (state.emailError) {
                                    onEvent(ContactUiEvent.ClearError(error = "email"))
                                }
                            },
                            text = state.email,
                            error = state.emailError,
                            extraContent = {},
                            defaultText = "Email",
                            onValueChange = {
                                onEvent(ContactUiEvent.OnEmailChange(it))
                            },
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),

                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )
                        )*/
                        /*This is textfield for the name, but I don't think we need the name.
                        ContactTextField(
                            text = state.name,
                            extraContent = {},
                            error = state.nameError,
                            defaultText = "Name",
                            onValueChange = {
                                onEvent(ContactUiEvent.OnNameChange(it))
                            },
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )*/
                        ContactTextField(
                            text = state.subject,
                            extraContent = {},
                            error = state.subjectError,
                            defaultText = "Subject",
                            onValueChange = {
                                onEvent(ContactUiEvent.OnSubjectChange(it))
                            },
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )
                        ContactTextField(
                            modifier = Modifier.height(200.dp),
                            text = state.content,
                            error = state.contentError,
                            extraContent = {
                                Text((400 - state.content.length).toString())
                            },
                            defaultText = "Content",
                            onValueChange = {
                                onEvent(ContactUiEvent.OnContentChange(it))
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    controller?.hide()
                                }
                            ),
                            singleLine = false,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            )
                        )
                    }
                }

                item {
                    ActionButton(
                        text = "Send",
                        isEnabled = /*state.email.isNotBlank() && state.name.isNotBlank() &&*/ state.subject.isNotBlank() && state.content.isNotBlank()
                    ) {
                        /*Passing the intent so it will be called only if there are no errors*/
                        onEvent(ContactUiEvent.AttemptToSend {
                            context.sendMail(
                                to = state.email,
                                subject = state.subject,
                                content = state.content,
                                onEvent = { onEvent(it)}
                            )
                        })
                    }
                }
                item {
                    /*Just makes it a bit more visible*/
                    Spacer(Modifier.height(12.dp))

                }

            }

        }
    }


}

@Preview
@Composable
fun ContactScreenPreview() {
    val viewModel = ContactViewModel()
    val state by viewModel.state.collectAsState()
    TrivitroTheme {
        ContactScreen(state = state, onEvent = { viewModel.onEvent(it) }, onBack = {})
    }
}