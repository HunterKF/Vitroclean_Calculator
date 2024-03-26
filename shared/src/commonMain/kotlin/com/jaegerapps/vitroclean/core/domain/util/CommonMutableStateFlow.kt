package com.jaegerapps.vitroclean.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

expect class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>): MutableStateFlow<T>

fun <T> StateFlow<T>.toCommonMutableStateFlow() = MutableStateFlow(this)