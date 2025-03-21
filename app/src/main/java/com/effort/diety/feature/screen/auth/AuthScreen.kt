package com.effort.diety.feature.screen.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.effort.diety.R
import com.effort.diety.feature.screen.common.CustomButton
import com.effort.diety.feature.screen.common.CustomTextField
import com.effort.diety.presentation.UiState
import com.effort.diety.presentation.auth.AuthViewModel

@Composable
fun AuthUi(
    authViewModel: AuthViewModel = hiltViewModel(),
    startMainActivity: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
    ) {

        val signUpState by authViewModel.signUpState.collectAsStateWithLifecycle()
        val signInState by authViewModel.signInState.collectAsStateWithLifecycle()

        LaunchedEffect(signInState) {
            if (signInState is UiState.Success) {
                startMainActivity()
            }
        }
        AuthForm(
            signUpState = signUpState,
            signInState = signInState,
            onLogIn = { email, password ->
                authViewModel.signIn(email, password)
            },
            onSignUp = { email, password ->
                authViewModel.signUp(email, password)
            }
        )
    }
}

@Composable
fun AuthForm(
    signUpState: UiState<Unit>,
    signInState: UiState<Unit>,
    onLogIn: (String, String) -> Unit,
    onSignUp: (String, String) -> Unit,
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier
                .padding(top = 100.dp, bottom = 32.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = stringResource(R.string.email)
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = stringResource(R.string.password),
            isPassword = true
        )

        when (signUpState) {
            is UiState.Error -> {
                Text(
                    text = signUpState.exception.message
                        ?: stringResource(R.string.signup_failure_message),
                    color = Color.Red,
                    modifier = Modifier.padding(10.dp),
                    fontSize = 16.sp
                )
            }

            else -> {}
        }

        when (signInState) {
            is UiState.Error -> {
                Text(
                    text = signInState.exception.message
                        ?: stringResource(R.string.login_failure_message),
                    color = Color.Red,
                    modifier = Modifier.padding(10.dp),
                    fontSize = 16.sp
                )
            }

            else -> {}
        }

        if (signUpState is UiState.Loading || signInState is UiState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.padding(10.dp),
                color = Color.White
            )
        }

        CustomButton(
            text = stringResource(R.string.login),
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.login_input_hint_message), Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    onLogIn(email, password)
                }
            },
            containerColor = Color.Blue,
            contentColor = Color.White,
            enabled = signInState !is UiState.Loading
        )

        CustomButton(
            text = stringResource(R.string.signup),
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, R.string.login_input_hint_message, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    onSignUp(email, password)
                }
            },
            containerColor = Color.DarkGray,
            contentColor = Color.Black,
            enabled = signUpState !is UiState.Loading
        )
    }
}