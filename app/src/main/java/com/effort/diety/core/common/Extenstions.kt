package com.effort.diety.core.common

import com.effort.diety.presentation.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun handleAuthResult(
    stateFlow: MutableStateFlow<UiState<Unit>>,
    authAction: suspend () -> Any?,
    errorMessage: String,
    coroutineScope: CoroutineScope
) {
    stateFlow.value = UiState.Loading

    coroutineScope.launch {
        try {
            val user = authAction()
            stateFlow.value = when {
                user != null -> UiState.Success(Unit)
                else -> UiState.Error(Exception(errorMessage))
            }
        } catch (e: Exception) {
            stateFlow.value = UiState.Error(e)
        }
    }
}
