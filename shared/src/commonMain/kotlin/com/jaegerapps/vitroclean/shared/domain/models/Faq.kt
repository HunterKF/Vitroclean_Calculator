package com.jaegerapps.vitroclean.shared.domain.models


/*Holds a FAQ, has a question and an answer.
* Used in the FAQsScreen*/
data class Faq(
    val question: String,
    val answer: String
)