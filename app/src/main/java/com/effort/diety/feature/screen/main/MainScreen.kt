package com.effort.diety.feature.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.effort.diety.feature.screen.navigation.MainNavigation
import com.effort.diety.feature.model.NavigationTab
import com.effort.diety.feature.screen.diet.DietScreen
import com.effort.diety.feature.screen.home.HomeScreen
import com.effort.diety.feature.screen.profile.ProfileScreen
import com.effort.diety.presentation.diet.DietViewModel

@Composable
fun MainScreen(
    dietViewModel: DietViewModel = hiltViewModel()
) {
    var currentScreen by remember { mutableStateOf(NavigationTab.Home) }

    dietViewModel.loadExerciseData()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 80.dp)
                    .fillMaxSize()
            ) {
                when (currentScreen) {
                    NavigationTab.Home -> HomeScreen()
                    NavigationTab.Diet -> DietScreen()
                    NavigationTab.Profile -> ProfileScreen()
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            MainNavigation(
                currentScreen = currentScreen,
                onTabSelected = { currentScreen = it })
        }
    }
}