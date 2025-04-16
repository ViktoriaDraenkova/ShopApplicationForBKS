package com.practicum.testappshop.presentation.viewmodel.registration


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.testappshop.domain.auth.AuthInteractor

class RegistrationViewModelFactory(val authInteractor: AuthInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(authInteractor) as T
    }
}
