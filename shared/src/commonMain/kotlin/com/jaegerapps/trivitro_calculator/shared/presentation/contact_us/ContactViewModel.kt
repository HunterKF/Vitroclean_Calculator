package com.jaegerapps.trivitro_calculator.shared.presentation.contact_us

import com.jaegerapps.trivitro_calculator.core.domain.util.toCommonStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ContactViewModel {
    private val _state = MutableStateFlow(ContactState())

    val state = _state.toCommonStateFlow()
    fun onEvent(event: ContactUiEvent) {
        when (event) {
            ContactUiEvent.EmailSent -> TODO()
            is ContactUiEvent.OnContentChange -> {
                _state.update {
                    it.copy(
                        content = event.value.take(state.value.maxMessageCount),
                        error = if (event.value.length >= state.value.maxMessageCount) ContactError.LENGTH_TOO_LONG else null
                    )
                }
            }


            is ContactUiEvent.OnSubjectChange -> {
                _state.update {
                    it.copy(
                        subject = event.value.take(state.value.maxSubjectCount),
                        error = if (event.value.length >= state.value.maxSubjectCount) ContactError.LENGTH_TOO_LONG else null
                    )
                }
            }

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
            }


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
                checkEmailTextFields()
                if (!state.value.nameError && !state.value.subjectError && !state.value.emailError && !state.value.contentError) {
                    event.intent()
                }
            }

            is ContactUiEvent.SendError -> {
                _state.update {
                    it.copy(
                        error = event.error
                    )
                }
            }
        }
    }

    private fun checkEmailTextFields() {
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
    }
}