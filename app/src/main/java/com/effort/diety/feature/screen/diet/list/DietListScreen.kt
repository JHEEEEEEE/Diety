package com.effort.diety.feature.screen.diet.list

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effort.diety.R
import com.effort.diety.feature.diet.DietRecordInfoActivity
import com.effort.diety.ui.theme.DarkBlue
import com.effort.diety.ui.theme.GreenishTeal
import com.effort.diety.ui.theme.Purple

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
                text = stringResource(R.string.diet_records_label),
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
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }
            }

            is UiState.Success -> {
                val exerciseList = exerciseRecordState.data
                if (exerciseList.isEmpty()) {
                    item { Text(stringResource(R.string.no_exercise_data_available_message), color = Color.Gray, fontSize = 20.sp) }
                } else {
                    items(exerciseList) { exercise ->
                        ExerciseRow(exercise = exercise)
                    }
                }
            }

            is UiState.Error -> {
                item {
                    Text(
                        text = exerciseRecordState.exception.message ?: stringResource(R.string.data_loading_failure_message),
                        color = Color.Red,
                        fontSize = 20.sp
                    )
                }
            }

            is UiState.Empty -> {
                item { Text(stringResource(R.string.no_exercise_data_available_message), color = Color.Gray, fontSize = 20.sp) }
            }
        }
    }
}

@Composable
fun ExerciseRow(exercise: Exercise) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Purple, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                val intent = Intent(context, DietRecordInfoActivity::class.java)

                with(intent) {

                    putExtra("docId", exercise.docId)
                    putExtra("name", exercise.name)
                    putExtra("duration", exercise.duration.toString())
                    putExtra("calories", exercise.calories.toString())
                }

                context.startActivity(intent)
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = exercise.name,
                color = GreenishTeal,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(R.string.exercise_time_duration_label, exercise.duration),
                color = DarkBlue
            )
        }

        Text(
            text = stringResource(R.string.calories_kcal, exercise.calories),
            color = GreenishTeal,
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
                text = stringResource(R.string.plus_button_label),
                fontSize = 30.sp,
                color = Color.Black
            )
        }
    }
}