package com.effort.diety.feature.screen.profile.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effort.diety.R
import com.effort.diety.feature.screen.common.CustomGradientButton
import com.effort.diety.ui.theme.Blue
import com.effort.diety.ui.theme.Teal

@Composable
fun ProfileInfoScreen(
    name: String,
    age: String,
    height: String,
    onEditClicked: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(30.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.profile),
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.user_img),
                contentDescription = stringResource(R.string.profile_image_label),
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(50))
            )

            Spacer(modifier = Modifier.height(20.dp))

            ProfileInfo(label = stringResource(R.string.person_name), value = name)
            ProfileInfo(label = stringResource(R.string.person_age), value = "$age 세")
            ProfileInfo(label = stringResource(R.string.person_height), value = "$height cm")

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            CustomGradientButton(
                text = stringResource(R.string.edit),
                onClick = { onEditClicked() },
                gradientColors = listOf(Teal, Blue),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            )
        }
    }
}

@Composable
fun ProfileInfo(label: String, value: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) //외부 패딩
            .background(
                color = Color.DarkGray.copy(alpha = 0.5f), // background 투명도 50% 적용
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp) // 내부 패딩
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = value,
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}