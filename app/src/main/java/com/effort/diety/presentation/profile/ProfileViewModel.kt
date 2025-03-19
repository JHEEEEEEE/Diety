package com.effort.diety.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effort.diety.data.repository.ProfileRepository
import com.effort.diety.presentation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow<UiState<Triple<String, String, String>>>(UiState.Empty)
    val profileState get() = _profileState.asStateFlow()

    private val _saveState = MutableStateFlow<UiState<Boolean>>(UiState.Empty)
    val saveState get() = _saveState.asStateFlow()

    init {
        loadProfile()
    }

    // 프로필 불러오기
    private fun loadProfile() {
        viewModelScope.launch {
            _profileState.value = UiState.Loading

            try {
                val profileData = profileRepository.loadProfileData()
                _profileState.value = UiState.Success(profileData)
            } catch (e: Exception) {
                _profileState.value = UiState.Error(e)
            }
        }
    }

    // 프로필 저장
    fun saveProfile(name: String, age: String, height: String) {
        viewModelScope.launch {
            _saveState.value = UiState.Loading

            try {
                val result = profileRepository.saveProfileData(name, age, height)
                _saveState.value = UiState.Success(result)
            } catch (e: Exception) {
                _saveState.value = UiState.Error(e)
            }
        }
    }
}