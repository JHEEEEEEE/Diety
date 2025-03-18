package com.effort.diety.feature.screen.diet.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effort.diety.feature.model.Exercise
import com.effort.diety.feature.screen.common.AnimatedCircularProgressBar
import com.effort.diety.feature.screen.common.AnimatedText

@Composable
fun DietListScreen(
    onAddClicked: () -> Unit
) {

    val burnedCalories = 300
    val targetCalories = 1500

    val exerciseList = listOf(
        Exercise(name = "Running", duration = 30, calories = 200),
        Exercise(name = "Football", duration = 90, calories = 600),
    )

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

            AnimatedText(
                text = "Diet Records",
                color = Color.Blue,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.size(20.dp))

            Text(
                text = "$burnedCalories / $targetCalories kcal",
                color = Color.White,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.size(20.dp))

            Box(
                contentAlignment = Alignment.Center
            ) {
                AnimatedCircularProgressBar(
                    burnedCalories = 300,
                    targetCalories = 1500,
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 20.dp
                )
            }

            Spacer(modifier = Modifier.size(20.dp))

            LazyColumn {
                items(exerciseList) { exercise ->

                    ExerciseRow(exercise = exercise)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 20.dp, end = 20.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .clickable {
                    onAddClicked()
                },
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
            text = "${exercise.calories}kcal",
            color = Color(0xFF00BFAE),
        )
    }
}