package com.practicum.testappshop.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.practicum.testappshop.data.auth.AuthRepositoryImpl
import com.practicum.testappshop.data.cart.CartRepository
import com.practicum.testappshop.data.cart.impl.CartRepositoryImpl
import com.practicum.testappshop.data.db.AppDatabase
import com.practicum.testappshop.data.network.ProductsApiService
import com.practicum.testappshop.domain.products.ProductsRepository
import com.practicum.testappshop.data.products.impl.ProductsRepositoryImpl
import com.practicum.testappshop.data.purchases.PurchasesRepositoryImpl
import com.practicum.testappshop.domain.auth.AuthRepository
import com.practicum.testappshop.domain.purchases.PurchasesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    fun provideProductsApiService(retrofit: Retrofit): ProductsApiService {
        return retrofit.create(ProductsApiService::class.java)
    }

    @Provides
    fun provideProductsRepository(appDatabase: AppDatabase): ProductsRepository {
        return ProductsRepositoryImpl(appDatabase)
    }

    @Provides
    fun provideCartRepository(context: Context): CartRepository {
        return CartRepositoryImpl(context)
    }

    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, firebaseDatabase: FirebaseDatabase): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firebaseDatabase)
    }

    @Provides
    fun providePurchasesRepository(firebaseDatabase: FirebaseDatabase): PurchasesRepository {
        return PurchasesRepositoryImpl(firebaseDatabase)
    }
}