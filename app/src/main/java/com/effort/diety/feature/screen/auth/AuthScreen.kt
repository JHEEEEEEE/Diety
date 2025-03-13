package com.effort.diety.feature.screen.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.effort.diety.R
import com.effort.diety.feature.util.CustomButton
import com.effort.diety.feature.util.CustomTextField
import com.effort.diety.presentation.UiState
import com.effort.diety.presentation.auth.AuthViewModel
import com.effort.diety.ui.theme.DietyTheme

@Composable
fun AuthUi(
    authViewModel: AuthViewModel,
    startMainActivity: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        val signUpState by authViewModel.signUpState.collectAsStateWithLifecycle()
        val signInState by authViewModel.signInState.collectAsStateWithLifecycle()

        LaunchedEffect(signInState) {
            if (signInState is UiState.Success) {
                startMainActivity()
            }
        }

        AuthContent(
            paddingValues = innerPadding,
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
fun AuthContent(
    paddingValues: PaddingValues,
    signUpState: UiState<Unit>,
    signInState: UiState<Unit>,
    onLogIn: (String, String) -> Unit,
    onSignUp: (String, String) -> Unit,
) {
    Box(
        modifier = Modifier.padding(paddingValues)
    ) {
        AuthForm(
            signUpState, signInState, onLogIn, onSignUp
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
            .background(color = Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 100.dp, bottom = 32.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(100.dp))
        )

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email"
        )

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            isPassword = true
        )

        when (signUpState) {
            is UiState.Error -> {
                Text(
                    text = signUpState.exception.message ?: "회원가입 실패",
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
                    text = signInState.exception.message ?: "로그인 실패",
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
            text = "LogIn",
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please Enter Email Or Password", Toast.LENGTH_SHORT)
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
            text = "SignUp",
            onClick = {
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "Please Enter Email Or Password", Toast.LENGTH_SHORT)
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

@Preview(showBackground = true)
@Composable
fun PreviewAuthUi() {
    DietyTheme {

    }
}