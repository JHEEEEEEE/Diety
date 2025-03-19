package com.effort.diety.data.repository

import com.effort.diety.feature.model.Exercise

interface DietRepository {

    suspend fun saveExerciseData(exercise: Exercise)

    suspend fun loadExerciseData(): List<Exercise>

    suspend fun removeExerciseData(documentId: String)
}