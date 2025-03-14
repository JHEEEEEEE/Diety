package com.effort.diety.feature.screen.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.effort.diety.feature.model.NavigationTab

@Composable
fun MainNavigation(
    currentScreen: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Black)
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        NavigationTab.entries.forEach { screen ->

            val isSelected = currentScreen == screen

            val color by animateColorAsState(
                targetValue = if (isSelected) Color.White else Color.Gray,
                animationSpec = spring(),
                label = ""
            )

            val scale by animateFloatAsState(
                targetValue = if (isSelected) 1.2f else 1.0f,
                animationSpec = spring(),
                label = ""
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onTabSelected(screen) }
                    .scale(scale)
                    .padding(3.dp)
            ) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = screen.name,
                    tint = color
                )

                Text(
                    text = screen.name,
                    color = color
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainNavigation() {
    MainNavigation(NavigationTab.Home) { }
}