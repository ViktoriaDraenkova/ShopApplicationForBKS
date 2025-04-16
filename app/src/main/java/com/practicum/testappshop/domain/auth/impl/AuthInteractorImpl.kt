package com.practicum.testappshop.domain.auth.impl

import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.domain.auth.AuthRepository
import com.practicum.testappshop.domain.models.User
import kotlinx.coroutines.flow.Flow

class AuthInteractorImpl(private val authRepository: AuthRepository) : AuthInteractor {
    override suspend fun register(
        login: String,
        password: String,
        user: User
    ): Flow<Result<User>> {
        return authRepository.register(login, password, user)
    }

    override suspend fun signIn(login: String, password: String): Flow<Result<User>> {
        return authRepository.singIn(login, password)
    }

    override suspend fun getCurrentUser(): Flow<Result<User?>> {
        return authRepository.getCurrentUser()
    }
}