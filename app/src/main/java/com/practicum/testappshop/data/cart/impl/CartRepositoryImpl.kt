package com.practicum.testappshop.data.cart.impl

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practicum.testappshop.data.cart.CartRepository
import com.practicum.testappshop.domain.models.Product

class CartRepositoryImpl(context: Context) : CartRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val CART_KEY = "cart"
    }

    override fun addProductToCart(product: Product): Boolean {
        val cart: MutableMap<Long, Pair<Product, Long>> = getMutableCart()

        Log.d("REPO", "adding ${product}")
        if (cart.containsKey(product.id)) {
            Log.d("REPO1", "adding ${product}")

            val existingPair = cart[product.id]
            if (existingPair != null && existingPair.second < product.stock) {
                val newQuantity = existingPair.second + 1
                cart[product.id] = Pair(existingPair.first, newQuantity)
            } else {
                return false
            }
        } else {
            Log.d("REPO2", "adding ${product}")

            cart[product.id] = Pair(product, 1)
        }

        val editor = sharedPreferences.edit()
        editor.putString(CART_KEY, gson.toJson(cart))
        editor.apply()
        return true
    }

    override fun deleteProductFromCart(product: Product): Boolean {
        val cart: MutableMap<Long, Pair<Product, Long>> = getMutableCart()

        if (cart.containsKey(product.id)) {
            val existingPair = cart[product.id]
            if (existingPair != null) {
                if (existingPair.second - 1 != 0L) {
                    val newQuantity = existingPair.second - 1
                    cart[product.id] = Pair(existingPair.first, newQuantity)
                } else {
                    cart.remove(product.id)
                }
            } else {
                return false
            }
        }
        val editor = sharedPreferences.edit()
        editor.putString(CART_KEY, gson.toJson(cart))
        editor.apply()
        return true
    }

    override fun getCountInCart(product: Product): Long {
        val cart: MutableMap<Long, Pair<Product, Long>> = getMutableCart()
        return cart[product.id]?.second ?: 0
    }

    override fun getCart(): Map<Long, Pair<Product, Long>> = getMutableCart()

    override fun clearCart() {
        val editor = sharedPreferences.edit()
        editor.putString(CART_KEY, "{}")
        editor.apply()
    }

    private fun getMutableCart(): MutableMap<Long, Pair<Product, Long>> {
        val json = sharedPreferences.getString(CART_KEY, null)
        return if (json != null) {
            val type = object :
                TypeToken<MutableMap<Long, Pair<Product, Long>>>() {}.type// тип для десериализации
            gson.fromJson(json, type)
        } else {
            mutableMapOf()
        }
    }
}
