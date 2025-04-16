package com.practicum.testappshop.presentation.viewmodel.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.domain.models.User
import com.practicum.testappshop.presentation.states.AuthRegState
import kotlinx.coroutines.launch

class RegistrationViewModel(private val authInteractor: AuthInteractor) : ViewModel() {
    private val _authStateLiveData = MutableLiveData<AuthRegState>(AuthRegState.Filling)
    val authStateLiveData: LiveData<AuthRegState> get() = _authStateLiveData
//
//    fun isAuthorised() {
//        viewModelScope.launch {
//            authInteractor.getCurrentUser().collect { user ->
//                _userLiveData.postValue(user.getOrNull())
//            }
//        }
//    }

    fun register(login: String, password: String, user: User) {
        _authStateLiveData.postValue(AuthRegState.Loading)
        viewModelScope.launch {
            authInteractor.register(login, password, user).collect { result ->
                if (result.isSuccess) {
                    _authStateLiveData.postValue(AuthRegState.Authenticated(result.getOrNull()!!))
                } else {
                    _authStateLiveData.postValue(AuthRegState.Error(result.exceptionOrNull()?.message.toString()))
                    _authStateLiveData.postValue(AuthRegState.Filling)
                }
            }
        }
    }
}