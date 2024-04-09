package com.jaegerapps.vitroclean.android.presentation.contact_us_screen.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Log
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactUiEvent
import com.jaegerapps.vitroclean.shared.presentation.contact_us.ContactError

fun Context.sendMail(
    trivitroEmail: String = "sales@vitroclean.com",
    to: String,
    subject: String,
    content: String,
    onEvent: (ContactUiEvent) -> Unit,
) {
    /*Creates an intent for sending an email. If there is an error, we send it to the ViewModel*/
    try {
        val intent = Intent(Intent.ACTION_SEND)
        val subjectConstruct = "Vitroclean Inquiry - $subject"
        intent.type = "message/rfc822" // or "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(trivitroEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectConstruct)
        intent.putExtra(Intent.EXTRA_TEXT, content)
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        onEvent(ContactUiEvent.SendError(ContactError.NO_EMAIL_APP))
    } catch (t: Throwable) {
        Log.e("sendMail", t.message ?: "")
        onEvent(ContactUiEvent.SendError(ContactError.UNKNOWN_ERROR))
    }
}