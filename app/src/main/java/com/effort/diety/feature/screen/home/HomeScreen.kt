package com.effort.diety.feature.screen.home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.effort.diety.feature.model.Exercise
import com.effort.diety.feature.screen.common.AnimatedText
import com.effort.diety.feature.screen.common.ShiningText
import com.effort.diety.presentation.UiState
import com.effort.diety.presentation.diet.DietViewModel

@Composable
fun HomeScreen(
    dietViewModel: DietViewModel = hiltViewModel()
) {

    val exerciseRecordState by dietViewModel.exerciseRecordState.collectAsStateWithLifecycle()

    // UiState에서 List<Exercise> 추출
    val exerciseList: List<Exercise> = when (exerciseRecordState) {
        is UiState.Success -> (exerciseRecordState as UiState.Success).data
        else -> emptyList() // 로딩 중이거나 오류 발생 시 빈 리스트 반환
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        if (exerciseList.isNotEmpty()) {
            AnimatedExerciseGraph(exerciseList)
        } else {
            Text(
                text = "No exercise data available",
                color = Color.Gray,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun AnimatedExerciseGraph(exerciseList: List<Exercise>) {
    // 리스트가 비어 있을 경우 기본값 설정하여 예외 방지
    val maxDuration = exerciseList.maxOfOrNull { it.duration } ?: 1
    val maxCalories = exerciseList.maxOfOrNull { it.calories } ?: 1

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(15.dp))

        ShiningText()

        Spacer(modifier = Modifier.size(15.dp))

        Row(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            exerciseList.forEach { exercise ->

                val durationRatio = exercise.duration.toFloat() / maxDuration
                val caloriesRatio = exercise.calories.toFloat() / maxCalories

                val animatedDurationRatio = remember { Animatable(0f) }
                val animatedCaloriesRatio = remember { Animatable(0f) }

                LaunchedEffect(Unit) {
                    animatedDurationRatio.animateTo(
                        durationRatio, animationSpec = tween(durationMillis = 1000)
                    )

                    animatedCaloriesRatio.animateTo(
                        caloriesRatio, animationSpec = tween(durationMillis = 1000)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(animatedDurationRatio.value * 200.dp)
                            .background(
                                Color.Blue,
                                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                            )
                    )

                    Spacer(modifier = Modifier.size(4.dp))

                    Box(
                        modifier = Modifier
                            .width(24.dp)
                            .height(animatedCaloriesRatio.value * 200.dp)
                            .background(
                                Color.Red,
                                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                            )
                    )

                    Spacer(modifier = Modifier.size(4.dp))

                    Text(
                        text = exercise.name,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }
        }

        Column {
            AnimatedText(
                text = "Duration",
                color = Color.Blue,
                fontSize = 24.sp
            )

            AnimatedText(
                text = "Calories",
                color = Color.Red,
                fontSize = 24.sp
            )
        }
    }
}