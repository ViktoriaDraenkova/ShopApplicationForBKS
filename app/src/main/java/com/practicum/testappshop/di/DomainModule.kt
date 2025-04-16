package com.practicum.testappshop.di

import com.practicum.testappshop.data.cart.CartRepository
import com.practicum.testappshop.domain.products.ProductsRepository
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.domain.auth.AuthRepository
import com.practicum.testappshop.domain.auth.impl.AuthInteractorImpl
import com.practicum.testappshop.domain.cart.CartInteractor
import com.practicum.testappshop.domain.cart.impl.CartInteractorImpl
import com.practicum.testappshop.domain.products.ProductsInteractor
import com.practicum.testappshop.domain.products.impl.ProductsInteractorImpl
import com.practicum.testappshop.domain.purchases.PurchasesInteractor
import com.practicum.testappshop.domain.purchases.PurchasesRepository
import com.practicum.testappshop.domain.purchases.impl.PurchasesInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideProductsInteractor(productsRepository: ProductsRepository): ProductsInteractor {
        return ProductsInteractorImpl(productsRepository)
    }

    @Provides
    fun provideCartInteractor(cartRepository: CartRepository): CartInteractor {
        return CartInteractorImpl(cartRepository)
    }

    @Provides
    fun provideAuthInteractor(authRepository: AuthRepository): AuthInteractor {
        return AuthInteractorImpl(authRepository)
    }

    @Provides
    fun providePurchasesInteractor(purchasesRepository: PurchasesRepository): PurchasesInteractor {
        return PurchasesInteractorImpl(purchasesRepository)
    }
}