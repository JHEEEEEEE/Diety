package com.effort.diety.feature.screen.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.effort.diety.feature.model.ProfileDetailScreen.*
import com.effort.diety.feature.screen.profile.edit.ProfileEditScreen
import com.effort.diety.feature.screen.profile.info.ProfileInfoScreen
import com.effort.diety.presentation.UiState
import com.effort.diety.presentation.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var currentScreen by remember { mutableStateOf(ProfileInfo) }

    val profileState by profileViewModel.profileState.collectAsStateWithLifecycle()
    val saveState by profileViewModel.saveState.collectAsStateWithLifecycle()

    var name by rememberSaveable { mutableStateOf("이름을 입력해주세요.") }
    var age by rememberSaveable { mutableStateOf("0") }
    var height by rememberSaveable { mutableStateOf("0") }

    LaunchedEffect(profileState) {
        if (profileState is UiState.Success) {
            val (loadedName, loadedAge, loadedHeight) = (
                    profileState as UiState.Success
                    ).data

            name =  loadedName
            age = loadedAge
            height = loadedHeight
        } else  {
            val defaultName = "Unknown Name"
            val defaultAge = "Unknown Age"
            val defaultHeight = "Unknown Height"

            name = defaultName
            age = defaultAge
            height = defaultHeight
        }
    }

    LaunchedEffect(saveState) {
        when (saveState) {
            is UiState.Success -> {
                Toast.makeText(context, "프로필 저장 성공!", Toast.LENGTH_SHORT).show()
                currentScreen = ProfileInfo
            }

            is UiState.Error -> {
                val errorMessage = (saveState as UiState.Error).exception.message ?: "프로필 저장 실패"
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    when (currentScreen) {

        ProfileInfo -> ProfileInfoScreen(
            name = name,
            age = age,
            height = height,
            onEditClicked = { currentScreen = ProfileEdit }
        )

        ProfileEdit -> ProfileEditScreen(
            initialName = name,
            initialAge = age,
            initialHeight = height,
            onSaveClicked = { newName, newAge: String, newHeight: String ->

                name = newName
                age = newAge
                height = newHeight

                // 저장하기
                profileViewModel.saveProfile(name, age, height)

                currentScreen = ProfileInfo
            },
            onCancelClicked = {
                currentScreen = ProfileInfo
            }
        )
    }
}