package com.practicum.testappshop.presentation.viewmodel.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.domain.cart.CartInteractor
import com.practicum.testappshop.domain.models.Product
import com.practicum.testappshop.domain.models.Purchase
import com.practicum.testappshop.domain.models.PurchaseItem
import com.practicum.testappshop.domain.purchases.PurchasesInteractor
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CartViewModel(
    private val cartInteractor: CartInteractor,
    private val purchasesInteractor: PurchasesInteractor,
    private val authInteractor: AuthInteractor
) : ViewModel() {

    fun increaseInCart(product: Product): Boolean {
        return cartInteractor.addProductToCart(product)
    }

    fun decreaseInCart(product: Product): Boolean {
        return cartInteractor.deleteProductFromCart(product)
    }

    fun getCart(): List<Pair<Product, Long>> {
        return cartInteractor.getCart().values.toList()
    }

    fun buy(onSuccess: OnCartUpdate) {
        viewModelScope.launch {
            authInteractor.getCurrentUser().collect { user ->
                if (user.isSuccess) {
                    val cart = getCart()
                    val purchase = Purchase(
                        getCurrentDateTime(),
                        cart.map { item -> PurchaseItem(item.first, item.second.toInt()) }
                    )
                    purchasesInteractor.addPurchase(user.getOrNull()!!, purchase).collect { buyResult ->
                        if (buyResult.isSuccess) {
                            cartInteractor.clearCart()
                            onSuccess.updateCart()
                        }
                    }
                }
            }
        }
    }

    private fun getCurrentDateTime(): String {
        val currentDate = Date()
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        return format.format(currentDate)
    }

    fun interface OnCartUpdate {
        fun updateCart()
    }
}