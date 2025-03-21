package com.effort.diety.feature.screen.diet.record

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.effort.diety.R
import com.effort.diety.feature.model.Exercise
import com.effort.diety.feature.screen.common.AnimatedText
import com.effort.diety.feature.screen.common.CustomGradientButton
import com.effort.diety.feature.screen.common.CustomTextField
import com.effort.diety.presentation.diet.DietViewModel
import com.effort.diety.ui.theme.PurpleDark
import com.effort.diety.ui.theme.PurpleLight

@Composable
fun DietRecordScreen(
    dietViewModel: DietViewModel = hiltViewModel(),
    onSaveClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {

    val context = LocalContext.current

    var exerciseType by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Spacer(modifier = Modifier.size(20.dp))

            AnimatedText(
                text = stringResource(R.string.diet_record_input_title),
                color = Color.White,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.size(10.dp))

            CustomTextField(
                value = exerciseType,
                onValueChange = { exerciseType = it },
                placeholder = stringResource(R.string.exercise_type_label)
            )
            Spacer(modifier = Modifier.size(10.dp))

            CustomTextField(
                value = calories,
                onValueChange = { calories = it },
                placeholder = stringResource(R.string.calorie_label)
            )
            Spacer(modifier = Modifier.size(10.dp))

            CustomTextField(
                value = duration,
                onValueChange = { duration = it },
                placeholder = stringResource(R.string.exercise_duration_label)
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
        ) {

            CustomGradientButton(
                text = stringResource(R.string.save_button_label),
                onClick = {

                    val caloriesValue = calories.toIntOrNull()
                    val durationValue = duration.toIntOrNull()

                    if (exerciseType.isEmpty() || caloriesValue == null || durationValue == null) {
                        Toast.makeText(context,
                            context.getString(R.string.invalid_input_message), Toast.LENGTH_SHORT).show()
                    } else {

                        val exercise = Exercise(
                            docId = "",
                            name = exerciseType,
                            calories = caloriesValue,
                            duration = durationValue
                        )

                        dietViewModel.saveExerciseData(exercise)

                        onSaveClicked()
                    }
                },
                gradientColors = listOf(PurpleDark, PurpleLight)
            )

            Spacer(modifier = Modifier.size(10.dp))

            CustomGradientButton(
                text = stringResource(R.string.cancel_button_label),
                onClick = {
                    onCancelClicked()
                },
                gradientColors = listOf(Color.Gray, Color.DarkGray)
            )
        }
    }
}