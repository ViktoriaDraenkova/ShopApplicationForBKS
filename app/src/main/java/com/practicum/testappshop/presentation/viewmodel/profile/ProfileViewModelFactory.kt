package com.practicum.testappshop.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.domain.purchases.PurchasesInteractor
import com.practicum.testappshop.presentation.viewmodel.home.HomeViewModel
import com.practicum.testappshop.presentation.viewmodel.registration.RegistrationViewModel

class ProfileViewModelFactory(private val authInteractor: AuthInteractor, private val purchasesInteractor: PurchasesInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(authInteractor, purchasesInteractor) as T
    }
}