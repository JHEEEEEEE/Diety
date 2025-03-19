package com.effort.diety.feature.screen.diet.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.effort.diety.feature.model.Exercise
import com.effort.diety.feature.screen.common.AnimatedCircularProgressBar
import com.effort.diety.feature.screen.common.AnimatedText
import com.effort.diety.presentation.UiState
import com.effort.diety.presentation.diet.DietViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DietListScreen(
    dietViewModel: DietViewModel = hiltViewModel(),
    onAddClicked: () -> Unit
) {
    // 운동 데이터 상태 관리
    val exerciseRecordState by dietViewModel.exerciseRecordState.collectAsStateWithLifecycle()

    // 화면 진입 시 운동 데이터 불러오기
    LaunchedEffect(Unit) {
        dietViewModel.loadExerciseData()
    }

    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(20.dp))

            // 제목
            AnimatedText(
                text = "Diet Records",
                color = Color.Blue,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.size(20.dp))

            // 칼로리 정보
            val targetCalories = 1500
            val burnedCalories = when (val state = exerciseRecordState) {
                is UiState.Success -> {
                    state.data.sumOf { it.calories }
                }

                else -> 0
            }
            Text(
                text = "$burnedCalories / $targetCalories kcal",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.size(20.dp))

            Box(contentAlignment = Alignment.Center) {
                AnimatedCircularProgressBar(
                    burnedCalories = burnedCalories,
                    targetCalories = targetCalories,
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 20.dp
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            // 운동 기록 리스트
            DietExerciseList(exerciseRecordState)
        }
    }

    // 운동 추가 버튼 (+ 버튼)
    FloatingAddButton(onAddClicked)
}

@Composable
fun DietExerciseList(exerciseRecordState: UiState<List<Exercise>>) {
    LazyColumn {
        when (exerciseRecordState) {
            is UiState.Loading -> {
                item { Text("Loading...", color = Color.White) }
            }

            is UiState.Success -> {
                val exerciseList = exerciseRecordState.data
                if (exerciseList.isEmpty()) {
                    item { Text("운동 데이터가 없습니다.", color = Color.Gray) }
                } else {
                    items(exerciseList) { exercise ->
                        ExerciseRow(exercise = exercise)
                    }
                }
            }

            is UiState.Error -> {
                val errorMessage = exerciseRecordState.exception.message ?: "데이터를 불러오지 못했습니다."
                item { Text(errorMessage, color = Color.Red) }
            }

            is UiState.Empty -> {
                item { Text("운동 데이터가 없습니다.", color = Color.Gray) }
            }
        }
    }
}

@Composable
fun ExerciseRow(exercise: Exercise) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color(0xFF500371), shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = exercise.name,
                color = Color(0xFF00BFAE),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${exercise.duration} 분",
                color = Color(0xFF007DBF)
            )
        }

        Text(
            text = "${exercise.calories} kcal",
            color = Color(0xFF00BFAE),
        )
    }
}

@Composable
fun FloatingAddButton(onAddClicked: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 20.dp, end = 20.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .clickable { onAddClicked() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                fontSize = 30.sp,
                color = Color.Black
            )
        }
    }
}