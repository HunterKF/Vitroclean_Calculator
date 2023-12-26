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
    val sent: Boolean = false,
    val maxMessageCount: Int = 500,
    val maxSubjectCount: Int = 120
)

enum class ContactError {
    INVALID_EMAIL,
    LENGTH_TOO_LONG,
    DATA_MISSING,
    NO_EMAIL_APP,
    UNKNOWN_ERROR
}