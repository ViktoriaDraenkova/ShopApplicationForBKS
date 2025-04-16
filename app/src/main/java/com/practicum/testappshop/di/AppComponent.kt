package com.practicum.testappshop.di

import com.practicum.testappshop.MainActivity
import com.practicum.testappshop.ui.authorisation.AuthorisationFragment
import com.practicum.testappshop.ui.cart.CartFragment
import com.practicum.testappshop.ui.home.HomeFragment
import com.practicum.testappshop.ui.prodile.ProfileFragment
import com.practicum.testappshop.ui.product_details.ProductDetailsFragment
import com.practicum.testappshop.ui.registration.RegistrationFragment
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(cartFragment: CartFragment)
    fun inject(detailsFragment: ProductDetailsFragment)
    fun inject(authFragment: AuthorisationFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(registrationFragment: RegistrationFragment)
}