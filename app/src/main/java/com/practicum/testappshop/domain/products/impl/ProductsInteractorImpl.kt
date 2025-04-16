package com.practicum.testappshop.domain.products.impl

import com.practicum.testappshop.domain.models.Product
import com.practicum.testappshop.domain.products.ProductsInteractor
import com.practicum.testappshop.domain.products.ProductsRepository
import kotlinx.coroutines.flow.Flow

class ProductsInteractorImpl(private val productsRepository: ProductsRepository) :
    ProductsInteractor {
    override fun getProducts(): Flow<Result<List<Product>>> {
        return productsRepository.getAllProducts()
    }
}