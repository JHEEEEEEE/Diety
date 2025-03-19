package com.effort.diety.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.effort.diety.feature.screen.main.MainScreen
import com.effort.diety.presentation.diet.DietViewModel
import com.effort.diety.ui.theme.DietyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val dietViewModel: DietViewModel by viewModels() // ViewModel 주입

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DietyTheme {
                MainScreen()
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        dietViewModel.loadExerciseData() // Activity가 다시 보일 때 데이터 새로고침
    }
}