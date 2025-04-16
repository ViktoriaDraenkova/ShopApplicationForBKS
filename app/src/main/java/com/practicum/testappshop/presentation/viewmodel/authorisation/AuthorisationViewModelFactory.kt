package com.practicum.testappshop.presentation.viewmodel.authorisation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.presentation.viewmodel.cart.CartViewModel

class AuthorisationViewModelFactory(val authorisationInteractor: AuthInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthorisationViewModel(authorisationInteractor) as T
    }
}
