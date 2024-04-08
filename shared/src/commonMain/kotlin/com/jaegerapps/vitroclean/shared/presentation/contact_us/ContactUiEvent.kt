package com.jaegerapps.vitroclean.shared.presentation.contact_us

sealed class ContactUiEvent {
//    data class OnEmailChange(val value: String) : ContactUiEvent()
    //change subject textfield value
    data class OnSubjectChange(val value: String) : ContactUiEvent()
    //change content message textfield value

    data class OnContentChange(val value: String) : ContactUiEvent()
//    data class OnNameChange(val value: String) : ContactUiEvent()
    //clear ui red border error, not really used now
    data class ClearError(val error: String) : ContactUiEvent()
    //Clears the snackbar error, currently in use
    data object ClearSnackbar : ContactUiEvent()
    //When subject and content are written and user clicks send. Should open local email app
    data class AttemptToSend(val intent: () -> Unit) : ContactUiEvent()
    //Handled natively, returns an error from the native side, like in the intent. Only happens if there is a device error
    data class SendError(val error: ContactError) : ContactUiEvent()
}