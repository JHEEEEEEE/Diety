package com.effort.diety.feature.screen.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.effort.diety.R
import kotlinx.coroutines.delay

@Composable
fun ShiningText() {

    var isShining by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        isShining = false
    }

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val transAnim = infiniteTransition.animateFloat(
        initialValue = -1000f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        if (isShining) {
            Text(
                text = stringResource(R.string.exercise_data_title),
                style = TextStyle(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.8f),
                            Color.Transparent
                        ),
                        start = Offset(transAnim.value, 0f),
                        end = Offset(transAnim.value + 500f, 0f)
                    )
                )
            )
        } else {
            Text(
                text = stringResource(R.string.exercise_data_title),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

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

    LaunchedEffect(burnedCalories) {
        animatedProgress.animateTo(
            targetValue = if (targetCalories > 0) burnedCalories.toFloat() / targetCalories else 0f,
            animationSpec = tween(durationMillis = 1000)
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
