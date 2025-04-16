package com.practicum.testappshop.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.practicum.testappshop.domain.auth.AuthRepository
import com.practicum.testappshop.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase
) : AuthRepository {

    override suspend fun register(
        login: String,
        password: String,
        user: User
    ): Flow<Result<User>> = flow {
        try {
            val result = firebaseAuth.createUserWithEmailAndPassword(login, password).await()
            if (result.user == null) {
                emit(Result.failure(Exception("Got empty user from Firebase")))
            }

            val uid = result.user!!.uid
            database.getReference("users").child(uid).setValue(user).await()

            emit(Result.success(user))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun singIn(login: String, password: String): Flow<Result<User>> = flow {
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(login, password).await()
            if (result.user == null) {
                emit(Result.failure(Exception("Пользователь не найден")))
            }

            emit(getUserFromDb(result.user!!.uid))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override suspend fun getCurrentUser(): Flow<Result<User?>> = flow {
        val curUser = firebaseAuth.currentUser
        if (curUser == null) {
            emit(Result.success(null))
        } else {
            emit(getUserFromDb(curUser.uid))
        }
    }

    private suspend fun getUserFromDb(uid: String): Result<User> {
        val snapshot = database.getReference("users").child(uid).get().await()
        return if (snapshot.exists()) {
            val user = snapshot.getValue(User::class.java)
            if (user != null) {
                user.id = uid
                Result.success(user)
            } else {
                Result.failure(Exception("Can't parse user"))
            }
        } else {
            Result.failure(Exception("Пользователь не найден"))
        }
    }
}