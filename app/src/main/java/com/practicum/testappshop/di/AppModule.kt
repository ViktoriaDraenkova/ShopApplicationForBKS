package com.practicum.testappshop.di

import android.content.Context
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.domain.cart.CartInteractor
import com.practicum.testappshop.domain.products.ProductsInteractor
import com.practicum.testappshop.domain.purchases.PurchasesInteractor
import com.practicum.testappshop.presentation.viewmodel.authorisation.AuthorisationViewModelFactory
import com.practicum.testappshop.presentation.viewmodel.cart.CartViewModelFactory
import com.practicum.testappshop.presentation.viewmodel.home.HomeViewModelFactory
import com.practicum.testappshop.presentation.viewmodel.product_details.ProductViewModelFactory
import com.practicum.testappshop.presentation.viewmodel.profile.ProfileViewModelFactory
import com.practicum.testappshop.presentation.viewmodel.registration.RegistrationViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideHomeViewModelFactory(
        productsInteractor: ProductsInteractor,
        cartInteractor: CartInteractor
    ): HomeViewModelFactory {
        return HomeViewModelFactory(productsInteractor, cartInteractor)
    }

    @Provides
    fun provideProductDetailsViewModelFactory(
        cartInteractor: CartInteractor
    ): ProductViewModelFactory {
        return ProductViewModelFactory(cartInteractor)
    }

    @Provides
    fun provideCartViewModelFactory(
        cartInteractor: CartInteractor,
        purchasesInteractor: PurchasesInteractor,
        authInteractor: AuthInteractor,
    ): CartViewModelFactory {
        return CartViewModelFactory(cartInteractor, purchasesInteractor, authInteractor)
    }

    @Provides
    fun provideRegistrationViewModelFactory(
        authorisationInteractor: AuthInteractor
    ): RegistrationViewModelFactory {
        return RegistrationViewModelFactory(authorisationInteractor)
    }

    @Provides
    fun provideAuthorisationViewModelFactory(
        authorisationInteractor: AuthInteractor
    ): AuthorisationViewModelFactory {
        return AuthorisationViewModelFactory(authorisationInteractor)
    }

    @Provides
    fun provideProfileViewModelFactory(
        authorisationInteractor: AuthInteractor,
        purchasesInteractor: PurchasesInteractor
    ): ProfileViewModelFactory {
        return ProfileViewModelFactory(authorisationInteractor, purchasesInteractor)
    }
}