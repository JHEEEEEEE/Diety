package com.effort.diety.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effort.diety.data.repository.AuthRepository
import com.effort.diety.presentation.util.handleAuthResult
import com.effort.diety.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // 회원가입 상태 관리
    private val _signUpState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val signUpState get() = _signUpState.asStateFlow()

    // 로그인 상태 관리
    private val _signInState = MutableStateFlow<UiState<Unit>>(UiState.Empty)
    val signInState get() = _signInState.asStateFlow()

    // 회원가입
    fun signUp(email: String, password: String) {
        handleAuthResult(
            stateFlow = _signUpState,
            authAction = { authRepository.signUp(email, password) },
            errorMessage = "회원가입 실패",
            coroutineScope = viewModelScope
        )
    }

    // 로그인
    fun signIn(email: String, password: String) {
        handleAuthResult(
            stateFlow = _signInState,
            authAction = { authRepository.signIn(email, password) },
            errorMessage = "로그인 실패",
            coroutineScope = viewModelScope
        )
    }
}