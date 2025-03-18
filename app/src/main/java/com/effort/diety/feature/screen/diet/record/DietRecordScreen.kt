package com.effort.diety.feature.screen.diet.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effort.diety.feature.screen.common.AnimatedText
import com.effort.diety.feature.screen.common.CustomGradientButton
import com.effort.diety.feature.screen.common.CustomTextField

@Composable
fun DietRecordScreen(onSaveClicked: () -> Unit, onCancelClicked: () -> Unit) {

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
                text = "Diet Record Input",
                color = Color.White,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.size(10.dp))

            CustomTextField(
                value = "",
                onValueChange = {},
                placeholder = "운동 종류"
            )
            Spacer(modifier = Modifier.size(10.dp))

            CustomTextField(
                value = "",
                onValueChange = {},
                placeholder = "칼로리"
            )
            Spacer(modifier = Modifier.size(10.dp))

            CustomTextField(
                value = "",
                onValueChange = {},
                placeholder = "시간"
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
                text = "저장",
                onClick = {
                    onSaveClicked()
                },
                gradientColors = listOf(Color(0xFF6A189A), Color(0xFFAB47BC))
            )

            Spacer(modifier = Modifier.size(10.dp))

            CustomGradientButton(
                text = "취소",
                onClick = {
                    onCancelClicked()
                },
                gradientColors = listOf(Color.Gray, Color.DarkGray)
            )
        }
    }
}