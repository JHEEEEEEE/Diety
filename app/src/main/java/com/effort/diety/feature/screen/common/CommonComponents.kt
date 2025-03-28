package com.effort.diety.feature.screen.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)  // 바깥쪽 여백
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp) // 안쪽 여백
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.Gray,
                                style = TextStyle(fontSize = 16.sp)
                            )
                        }
                        innerTextField()
                    }

                    if (isPassword) {
                        IconButton(
                            onClick = { passwordVisible = !passwordVisible },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Close else Icons.Default.Check,
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
        )
    }
}

@Composable
fun CustomGradientButton(
    text: String,
    onClick: () -> Unit,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(colors = gradientColors),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun CircularProgressBar(
    progress: Float,
    modifier: Modifier,
    strokeWidth: Dp,
    color: Color = Color.Blue,
    backgroundColor : Color = Color.LightGray
) {

    Canvas(modifier = modifier){

        val size = size.minDimension
        val radius = size / 2f
        val center = Offset(size / 2f, size / 2f)
        val startAngle = -90f
        val strokeWidthPx = strokeWidth.toPx()

        drawCircle(
            color = backgroundColor,
            radius = radius - strokeWidthPx / 2,
            center = center,
            style = Stroke(width = strokeWidthPx)
        )

        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = progress * 360,
            useCenter = false,
            topLeft = Offset(strokeWidthPx / 2, strokeWidthPx / 2),
            size = Size(size - strokeWidthPx, size - strokeWidthPx),
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
        )
    }
}