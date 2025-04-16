package com.practicum.testappshop.domain.purchases

import com.practicum.testappshop.domain.models.Purchase
import com.practicum.testappshop.domain.models.User
import kotlinx.coroutines.flow.Flow

interface PurchasesRepository {
    suspend fun getPurchases(user: User): Flow<Result<List<Purchase>>>
    suspend fun addPurchase(user: User, purchase: Purchase):Flow<Result<Unit>>
}