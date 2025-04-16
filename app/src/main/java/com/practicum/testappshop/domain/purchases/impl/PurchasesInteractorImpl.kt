package com.practicum.testappshop.domain.purchases.impl

import com.practicum.testappshop.domain.models.Purchase
import com.practicum.testappshop.domain.models.User
import com.practicum.testappshop.domain.purchases.PurchasesInteractor
import com.practicum.testappshop.domain.purchases.PurchasesRepository
import kotlinx.coroutines.flow.Flow

class PurchasesInteractorImpl(private val purchasesRepository: PurchasesRepository): PurchasesInteractor {
    override suspend fun getPurchases(user: User): Flow<Result<List<Purchase>>> {
        return purchasesRepository.getPurchases(user)
    }

    override suspend fun addPurchase(
        user: User,
        purchase: Purchase
    ): Flow<Result<Unit>> {
        return purchasesRepository.addPurchase(user, purchase)
    }
}