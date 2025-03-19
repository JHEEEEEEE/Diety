package com.effort.diety.data.repositoryimpl

import com.effort.diety.data.repository.DietRepository
import com.effort.diety.feature.model.Exercise
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DietRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : DietRepository {

    // 현재 로그인된 사용자 가져오기 (로그인하지 않은 경우 예외 발생)
    private fun requireCurrentUser(): FirebaseUser {
        return auth.currentUser ?: throw Exception("로그인이 필요합니다.")
    }

    // 운동 데이터 저장
    override suspend fun saveExerciseData(exercise: Exercise) {
        val user = requireCurrentUser()
        firestore.collection("diet_records")
            .document(user.uid)
            .collection("exercises")
            .add(exercise)
            .await()
    }

    // 운동 데이터 불러오기
    override suspend fun loadExerciseData(): List<Exercise> {
        val user = requireCurrentUser()
        val documents = firestore.collection("diet_records")
            .document(user.uid)
            .collection("exercises")
            .get()
            .await()

        return documents.documents.mapNotNull { document ->
            document.toObject(Exercise::class.java)?.copy(docId = document.id)
        }
    }

    // 운동 데이터 삭제
    override suspend fun removeExerciseData(documentId: String) {
        val user = requireCurrentUser()
        firestore.collection("diet_records")
            .document(user.uid)
            .collection("exercises")
            .document(documentId)
            .delete()
            .await()
    }
}