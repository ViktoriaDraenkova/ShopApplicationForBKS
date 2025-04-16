package com.practicum.testappshop.util

import com.practicum.testappshop.domain.models.Product
import com.practicum.testappshop.domain.models.Purchase

fun interface ProductClickListener {
    fun onProductClick(product: Product)
}

fun interface ButtonAddClickListener {
    fun onButtonAddClickListener(product: Product): Boolean
}

fun interface ButtonDelClickListener {
    fun onButtonDelClickListener(product: Product): Boolean
}

fun interface PurchaseClickListener {
    fun onPurchaseClick(purchase: Purchase)
}

