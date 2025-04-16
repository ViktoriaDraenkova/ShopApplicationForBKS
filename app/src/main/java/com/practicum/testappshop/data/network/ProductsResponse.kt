package com.practicum.testappshop.data.network

import com.practicum.testappshop.domain.models.Product

data class ProductsResponse(
    val products: List<Product>
)