package com.effort.diety.feature.screen.diet.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.effort.diety.feature.diet.DietRecordInfoActivity
import com.effort.diety.feature.screen.common.AnimatedText
import com.effort.diety.feature.screen.common.CustomGradientButton
import com.effort.diety.presentation.UiState
import com.effort.diety.presentation.diet.DietViewModel

@Composable
fun DietDetailInfo(
    docId: String,
    name: String,
    duration: String,
    calories: String,
    dietViewModel: DietViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activity = context as DietRecordInfoActivity

    // 삭제 성공 여부를 감지하는 StateFlow
    val removeExerciseState by dietViewModel.removeExerciseState.collectAsStateWithLifecycle()

    LaunchedEffect(removeExerciseState) {
        if (removeExerciseState is UiState.Success) {
            activity.finish() // 삭제 성공 시 Activity 종료
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(20.dp)
    ) {

        AnimatedText(
            text = "Info",
            color = Color.Blue,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "운동명 : $name",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = "운동시간 : $duration",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = "소모 칼로리 : $calories",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        CustomGradientButton(
            text = "삭제하기",
            onClick = {
                dietViewModel.removeExerciseData(docId)
                activity.finish()
            },
            gradientColors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
        )

        Spacer(modifier = Modifier.size(20.dp))

        CustomGradientButton(
            text = "나가기",
            onClick = {
                activity.finish()
            },
            gradientColors = listOf(Color.Gray, Color.DarkGray)
        )
    }
}