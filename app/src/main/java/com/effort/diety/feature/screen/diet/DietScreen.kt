package com.effort.diety.feature.screen.diet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.effort.diety.feature.model.DietDetailScreen.DietRecordAdd
import com.effort.diety.feature.model.DietDetailScreen.DietRecordList
import com.effort.diety.feature.screen.diet.list.DietListScreen
import com.effort.diety.feature.screen.diet.record.DietRecordScreen

@Composable
fun DietScreen() {

    var currentScreen by rememberSaveable { mutableStateOf(DietRecordList) }

    when (currentScreen) {

        DietRecordList -> {
            DietListScreen(
                onAddClicked = {
                    currentScreen = DietRecordAdd
                }
            )
        }

        DietRecordAdd -> {
            DietRecordScreen(
                onSaveClicked = {
                    currentScreen = DietRecordList
                },

                onCancelClicked = {
                    currentScreen = DietRecordList
                }
            )
        }
    }
}