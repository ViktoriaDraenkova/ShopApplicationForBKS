package com.practicum.testappshop.domain.products

import com.practicum.testappshop.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getAllProducts(): Flow<Result<List<Product>>>
}