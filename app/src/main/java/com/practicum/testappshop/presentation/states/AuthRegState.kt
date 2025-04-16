package com.practicum.testappshop.presentation.states

import com.practicum.testappshop.domain.models.User

sealed interface AuthRegState {
    data object Loading : AuthRegState

    data object Filling : AuthRegState

    data class Authenticated(val user: User) : AuthRegState

    data class Error(val message: String) : AuthRegState
}
