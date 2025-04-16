package com.practicum.testappshop.presentation.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.domain.cart.CartInteractor
import com.practicum.testappshop.domain.purchases.PurchasesInteractor

class CartViewModelFactory(
    private val cartInteractor: CartInteractor,
    private val purchasesInteractor: PurchasesInteractor,
    private val authInteractor: AuthInteractor
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(cartInteractor, purchasesInteractor, authInteractor) as T
    }
}