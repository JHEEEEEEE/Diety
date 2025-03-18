package com.effort.diety.feature.screen.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedText(text: String, color: Color, fontSize: TextUnit) {

    val animatedAlpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000)
        )
    }

    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        modifier = Modifier.alpha(animatedAlpha.value)
    )
}

@Composable
fun AnimatedCircularProgressBar(
    burnedCalories: Int,
    targetCalories: Int,
    modifier: Modifier,
    strokeWidth: Dp
) {

    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = burnedCalories.toFloat() / targetCalories,
            animationSpec = tween(durationMillis = 2000)
        )
    }

    CircularProgressBar(
        progress = animatedProgress.value,
        modifier = modifier,
        strokeWidth = strokeWidth
    )

    Text(
        text = "${(animatedProgress.value * 100).toInt()}%",
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}