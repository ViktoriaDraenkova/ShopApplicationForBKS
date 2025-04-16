package com.practicum.testappshop.domain.cart.impl

import android.util.Log
import com.practicum.testappshop.data.cart.CartRepository
import com.practicum.testappshop.domain.cart.CartInteractor
import com.practicum.testappshop.domain.models.Product

class CartInteractorImpl(private val cartRepository: CartRepository) : CartInteractor {
    override fun addProductToCart(product: Product): Boolean {
        Log.d("addcart", "DAAAA")
        return cartRepository.addProductToCart(product)
    }

    override fun deleteProductFromCart(product: Product): Boolean {
        return cartRepository.deleteProductFromCart(product)
    }

    override fun getCountInCart(product: Product): Long {
        return cartRepository.getCountInCart(product)
    }

    override fun getCart(): Map<Long, Pair<Product, Long>> {
        return cartRepository.getCart()
    }

    override fun clearCart() {
        cartRepository.clearCart()
    }
}