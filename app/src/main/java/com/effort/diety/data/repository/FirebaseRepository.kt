package com.effort.diety.data.repository

import com.google.firebase.auth.FirebaseUser

interface FirebaseRepository {

    suspend fun signUp(email: String, password: String): FirebaseUser?

    suspend fun signIn(email: String, password: String): FirebaseUser?
}