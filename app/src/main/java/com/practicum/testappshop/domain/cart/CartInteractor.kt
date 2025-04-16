package com.practicum.testappshop.domain.cart

import com.practicum.testappshop.domain.models.Product

interface CartInteractor {
    fun addProductToCart(product: Product): Boolean

    fun deleteProductFromCart(product: Product): Boolean

    fun getCountInCart(product: Product): Long

    fun getCart(): Map<Long, Pair<Product, Long>>

    fun clearCart()
}