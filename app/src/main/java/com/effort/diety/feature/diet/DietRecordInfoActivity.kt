package com.effort.diety.feature.diet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.effort.diety.feature.screen.diet.detail.DietDetailInfo
import com.effort.diety.ui.theme.DietyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DietRecordInfoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val docId = intent.getStringExtra("docId")
        val name = intent.getStringExtra("name")
        val duration = intent.getStringExtra("duration")
        val calories = intent.getStringExtra("calories")

        setContent {

            DietyTheme {
                DietDetailInfo(
                    docId = docId ?: "",
                    name = name ?: "",
                    duration = duration ?: "",
                    calories = calories ?: "",
                )
            }
        }
    }
}