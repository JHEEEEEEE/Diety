package com.effort.diety.data.repository

interface ProfileRepository {

    suspend fun saveProfileData(
        name: String,
        age: String,
        height: String
    ): Boolean

    suspend fun loadProfileData(): Triple<String, String, String>
}