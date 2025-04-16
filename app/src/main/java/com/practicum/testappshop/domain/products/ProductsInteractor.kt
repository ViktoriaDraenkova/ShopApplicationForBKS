package com.practicum.testappshop.domain.products

import com.practicum.testappshop.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface ProductsInteractor {

    fun getProducts(): Flow<Result<List<Product>>>

}