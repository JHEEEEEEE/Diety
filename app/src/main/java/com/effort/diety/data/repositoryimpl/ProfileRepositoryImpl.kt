package com.effort.diety.data.repositoryimpl

import com.effort.diety.data.repository.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ProfileRepository {

    override suspend fun saveProfileData(
        name: String,
        age: String,
        height: String
    ): Boolean {
        val user = getCurrentUser() ?: return false

        return try {
            val profile = hashMapOf(
                "name" to name,
                "age" to age,
                "height" to height
            )

            firestore.collection("profiles")
                .document(user.uid)
                .set(profile)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun loadProfileData(): Triple<String, String, String> {
        val user = getCurrentUser() ?: throw Exception("로그인이 필요합니다.")

        return try {
            val document = firestore.collection("profiles")
                .document(user.uid)
                .get()
                .await()

            if (document.exists()) {
                Triple(
                    document.getString("name") ?: "",
                    document.getString("age") ?: "",
                    document.getString("height") ?: ""
                )
            } else {
                throw Exception("프로필 데이터가 없습니다.")
            }
        } catch (e: Exception) {
            throw Exception("프로필 데이터를 불러오지 못했습니다.")
        }
    }

    // 현재 유저 가져오기 (필요할 때만 호출)
    private fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}