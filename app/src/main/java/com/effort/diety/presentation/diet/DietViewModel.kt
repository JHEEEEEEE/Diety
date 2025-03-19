package com.effort.diety.presentation.diet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effort.diety.data.repository.DietRepository
import com.effort.diety.feature.model.Exercise
import com.effort.diety.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DietViewModel @Inject constructor(
    private val dietRepository: DietRepository
) : ViewModel() {

    private val _exerciseRecordState = MutableStateFlow<UiState<List<Exercise>>>(UiState.Empty)
    val exerciseRecordState get() = _exerciseRecordState.asStateFlow()

    private val _saveExerciseState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
    val saveExerciseState get() = _saveExerciseState.asStateFlow()

    private val _removeExerciseState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
    val removeExerciseState get() = _removeExerciseState.asStateFlow()

    // 운동 데이터 저장
    fun saveExerciseData(exercise: Exercise) {
        viewModelScope.launch {
            try {
                _saveExerciseState.value = UiState.Loading
                dietRepository.saveExerciseData(exercise)
                _saveExerciseState.value = UiState.Success(true)
            } catch (e: Exception) {
                _saveExerciseState.value = UiState.Error(e)
            }
        }
    }

    // 운동 데이터 불러오기
    fun loadExerciseData() {
        viewModelScope.launch {
            try {
                _exerciseRecordState.value = UiState.Loading
                val exerciseList = dietRepository.loadExerciseData()
                _exerciseRecordState.value = UiState.Success(exerciseList)
            } catch (e: Exception) {
                _exerciseRecordState.value = UiState.Error(e)
            }
        }
    }

    // 운동 데이터 삭제
    fun removeExerciseData(documentId: String) {
        viewModelScope.launch {
            try {
                _removeExerciseState.value = UiState.Loading
                dietRepository.removeExerciseData(documentId)
                _removeExerciseState.value = UiState.Success(true)
            } catch (e: Exception) {
                _removeExerciseState.value = UiState.Error(e)
            }
        }
    }
}