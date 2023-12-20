package com.jaegerapps.trivitro_calculator.shared.presentation.contact_us

data class ContactState(
    val email: String = "",
    val name: String = "",
    val subject: String = "",
    val content: String = "",
    val emailError: Boolean = false,
    val nameError: Boolean = false,
    val subjectError: Boolean = false,
    val contentError: Boolean = false,
    val error: ContactError? = null,
    val sent: Boolean = false
)

enum class ContactError {
    INVALID_EMAIL,
    DATA_MISSING,
    NO_EMAIL_APP,
    UNKNOWN_ERROR
}