package com.practicum.testappshop.domain.auth

import com.practicum.testappshop.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(login: String, password: String, user: User): Flow<Result<User>>
    suspend fun singIn(login: String, password: String): Flow<Result<User>>
    suspend fun getCurrentUser(): Flow<Result<User?>>
}