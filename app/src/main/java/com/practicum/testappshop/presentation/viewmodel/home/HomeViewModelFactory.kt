package com.practicum.testappshop.presentation.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.testappshop.domain.cart.CartInteractor
import com.practicum.testappshop.domain.products.ProductsInteractor

class HomeViewModelFactory(
    private val productsInteractor: ProductsInteractor,
    private val cartInteractor: CartInteractor
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(productsInteractor, cartInteractor) as T
    }
}
