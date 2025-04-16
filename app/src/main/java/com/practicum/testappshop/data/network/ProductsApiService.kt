package com.practicum.testappshop.data.network

import retrofit2.http.GET

interface ProductsApiService {
    @GET("/products")
    suspend fun getAllProducts(): ProductsResponse
}
