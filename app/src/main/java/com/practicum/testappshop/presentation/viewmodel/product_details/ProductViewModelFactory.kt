package com.practicum.testappshop.presentation.viewmodel.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.testappshop.domain.cart.CartInteractor

class ProductViewModelFactory( private val cartInteractor: CartInteractor) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(cartInteractor) as T
    }
}
