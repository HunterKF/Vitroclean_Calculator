package com.jaegerapps.vitroclean.shared.presentation.contact_us

import com.jaegerapps.vitroclean.core.domain.util.toCommonStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ContactViewModel {
    private val _state = MutableStateFlow(ContactState())

    val state = _state.toCommonStateFlow()
    fun onEvent(event: ContactUiEvent) {
        when (event) {
            is ContactUiEvent.OnContentChange -> {
                //Changes the state's email content/message textfield. If it exceeds 500, error is LENGTH_TOO_LONG
                _state.update {
                    it.copy(
                        content = event.value.take(state.value.maxMessageCount),
                        error = if (event.value.length >= state.value.maxMessageCount) ContactError.LENGTH_TOO_LONG else null
                    )
                }
            }


            is ContactUiEvent.OnSubjectChange -> {
                //Changes the state's email subject textfield. If it exceeds 120, error is LENGTH_TOO_LONG
                _state.update {
                    it.copy(
                        subject = event.value.take(state.value.maxSubjectCount),
                        error = if (event.value.length >= state.value.maxSubjectCount) ContactError.LENGTH_TOO_LONG else null
                    )
                }
            }

           /* Originally the app was going to send an email from the app, but this was tabled. Keeping it in to add feature later if desired.
           is ContactUiEvent.OnNameChange -> _state.update {

                it.copy(
                    name = event.value
                )
            }
            is ContactUiEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = event.value
                    )
                }
            }*/


            is ContactUiEvent.ClearError -> {
                /*This was originally put in for adding red error borders, but has been decided not to be used.*/
                when (event.error) {
                    "email" -> {
                        _state.update {
                            it.copy(
                                emailError = !it.emailError
                            )
                        }
                    }

                    "subject" -> {
                        _state.update {
                            it.copy(
                                subjectError = !it.subjectError
                            )
                        }

                    }

                    "name" -> {
                        _state.update {
                            it.copy(
                                nameError = !it.nameError
                            )
                        }

                    }

                    "content" -> {
                        _state.update {
                            it.copy(
                                contentError = !it.contentError
                            )
                        }
                    }

                    else -> {

                    }
                }
            }

            ContactUiEvent.ClearSnackbar -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }

            is ContactUiEvent.AttemptToSend -> {
                //Loads local email apps. If it doesn't exist, it will throw an error inside the intent.
                // Not sure if this is the best way to do that though.
                checkEmailTextFields()
                if (!state.value.nameError && !state.value.subjectError && !state.value.emailError && !state.value.contentError) {
                    event.intent()
                }
            }

            is ContactUiEvent.SendError -> {
                //Sends an error from inside the intent on failure.
                _state.update {
                    it.copy(
                        error = event.error
                    )
                }
            }
        }
    }

    private fun checkEmailTextFields() {
        /*This shouldn't really matter because the button isn't enabled unless everything is filled, but keeping it in just in case.
        * User could still just have a lot of "      "s*/
        /*It doesn't seem like we need the name or email, so I have taken this out for now
        if (state.value.name.isBlank()) {
            _state.update {
                it.copy(
                    nameError = true,
                    error = it.error ?: ContactError.DATA_MISSING
                )
            }
        }
        if (state.value.email.isBlank() || !checkEmail(state.value.email)) {
            _state.update {
                it.copy(
                    emailError = true,
                    error = ContactError.INVALID_EMAIL
                )
            }
        }*/
        if (state.value.subject.isBlank()) {

            _state.update {
                it.copy(
                    subjectError = true,
                    error = it.error ?: ContactError.DATA_MISSING
                )
            }
        }
        if (state.value.content.isBlank()) {
            _state.update {
                it.copy(
                    contentError = true,
                    error = it.error ?: ContactError.DATA_MISSING
                )
            }
        }
    }

    /*Email was removed
    private fun checkEmail(email: String): Boolean {
        val emailAddressRegex = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        return email.matches(emailAddressRegex)
    }*/
}