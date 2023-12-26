package com.jaegerapps.trivitro_calculator.shared.presentation.contact_us

sealed class ContactUiEvent {
    data class OnEmailChange(val value: String) : ContactUiEvent()
    data class OnSubjectChange(val value: String) : ContactUiEvent()
    data class OnContentChange(val value: String) : ContactUiEvent()
    data class OnNameChange(val value: String) : ContactUiEvent()
    data class ClearError(val error: String) : ContactUiEvent()
    data object ClearSnackbar : ContactUiEvent()
    data class AttemptToSend(val intent: () -> Unit) : ContactUiEvent()
    data object EmailSent : ContactUiEvent()
    data class SendError(val error: ContactError) : ContactUiEvent()
}