package com.practicum.testappshop.presentation.viewmodel.authorisation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.testappshop.domain.auth.AuthInteractor
import com.practicum.testappshop.presentation.states.AuthRegState
import kotlinx.coroutines.launch

class AuthorisationViewModel(private val authInteractor: AuthInteractor) : ViewModel() {

    private val _userLiveData = MutableLiveData<AuthRegState>(AuthRegState.Filling)
    val userLiveData: LiveData<AuthRegState> get() = _userLiveData

    fun checkAuthorization() {
        viewModelScope.launch {
            _userLiveData.postValue(AuthRegState.Loading)
            authInteractor.getCurrentUser().collect { result ->
                if (result.isSuccess) {
                    val user = result.getOrNull()
                    if (user != null) {
                        _userLiveData.postValue(AuthRegState.Authenticated(user))
                    } else {
                        _userLiveData.postValue(AuthRegState.Filling)
                    }
                } else {
                    _userLiveData.postValue(AuthRegState.Error(result.exceptionOrNull()?.message.toString()))
                    _userLiveData.postValue(AuthRegState.Filling)
                }
            }
        }
    }

    fun login(login: String, password: String) {
        viewModelScope.launch {
            authInteractor.signIn(login, password).collect { result ->
                if (result.isSuccess) {
                    _userLiveData.postValue(AuthRegState.Authenticated(result.getOrNull()!!))
                } else {
                    _userLiveData.postValue(AuthRegState.Error(result.exceptionOrNull()?.message.toString()))
                }
            }
        }
    }
}