package com.effort.diety.feature.screen.profile.edit

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effort.diety.R
import com.effort.diety.feature.screen.common.CustomGradientButton
import com.effort.diety.ui.theme.PurpleDark
import com.effort.diety.ui.theme.PurpleLight

@Composable
fun ProfileEditScreen(
    initialName: String,
    initialAge: String,
    initialHeight: String,
    onSaveClicked: (String, String, String) -> Unit,
    onCancelClicked: () -> Unit
) {

    val context = LocalContext.current

    var name by remember { mutableStateOf(initialName) }
    var age by remember { mutableStateOf(initialAge) }
    var height by remember { mutableStateOf(initialHeight) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(30.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.edit_profile_button_label),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            ProfileEditField(label = stringResource(R.string.person_name), value = name, onValueChange = { name = it })
            ProfileEditField(label = stringResource(R.string.person_age), value = age, onValueChange = { age = it })
            ProfileEditField(label = stringResource(R.string.person_height), value = height, onValueChange = { height = it })

            Spacer(modifier = Modifier.height(20.dp))

            CustomGradientButton(
                text = stringResource(R.string.cancel_button_label),
                onClick = { onCancelClicked() },
                gradientColors = listOf(Color.Gray, Color.DarkGray)

            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomGradientButton(
                text = stringResource(R.string.save_button_label),
                onClick = {

                    if (name.isNotBlank() && age.all { it.isDigit() } && height.all { it.isDigit() }) {

                        onSaveClicked(name, age, height)

                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.hint_regiter_personal_info), Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                gradientColors = listOf(PurpleDark, PurpleLight)
            )
        }
    }
}

@Composable
fun ProfileEditField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            )
        )
    }
}