package com.effort.diety.feature.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.effort.diety.feature.model.ProfileDetailScreen.*
import com.effort.diety.feature.screen.profile.edit.ProfileEditScreen
import com.effort.diety.feature.screen.profile.info.ProfileInfoScreen

@Composable
fun ProfileScreen() {

    var currentScreen by remember { mutableStateOf(ProfileInfo) }

    var name by rememberSaveable { mutableStateOf("이름을 입력해주세요.") }
    var age by rememberSaveable { mutableStateOf("0") }
    var height by rememberSaveable { mutableStateOf("0") }

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
                currentScreen = ProfileInfo
            },
            onCancelClicked = {
                currentScreen = ProfileInfo
            }
        )
    }
}