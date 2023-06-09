package com.homairaahmed.bddoctorhub.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.data.User
import com.homairaahmed.bddoctorhub.repository.AuthRepository
import com.homairaahmed.bddoctorhub.utils.networkstate.AuthState
import com.homairaahmed.bddoctorhub.utils.networkstate.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val userName = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()
    val userPass = MutableLiveData<String>()
    var users = User()

    private val _user = MutableStateFlow(AuthState())
    val user: StateFlow<AuthState> = _user

    private val _userData = MutableStateFlow(UserState())
    val userData: StateFlow<UserState> = _userData

    fun login() {
        authRepository.login(userEmail.value.toString(), userPass.value.toString()).onEach {
            when (it) {
                is Resource.Loading -> {
                    _user.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {
                    _user.value = AuthState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _user.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun register() {
        authRepository.register(userEmail.value.toString(), userPass.value.toString(), users).onEach {
            when (it) {
                is Resource.Loading -> {
                    _user.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {
                    _user.value = AuthState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _user.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loggedUser() {

        authRepository.getLoggedUser().onEach {
            when (it) {
                is Resource.Loading -> {
                    _user.value = AuthState(isLoading = true)
                }
                is Resource.Error -> {
                    _user.value = AuthState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _user.value = AuthState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getUserData() {
        authRepository.getUserData().onEach {
            when (it) {
                is Resource.Loading -> {
                    _userData.value = UserState(isLoading = true)
                }
                is Resource.Error -> {
                    _userData.value = UserState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _userData.value = UserState(data = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loginDataValidation(): Int {
        if (userEmail.value.isNullOrEmpty()){
            return 1
        }
        if (userPass.value.isNullOrEmpty()){
            return 2
        }
        return 200
    }

    fun registerDataValidation(): Int {
        if (userName.value.isNullOrEmpty()){
            return 1
        }
        if (userEmail.value.isNullOrEmpty()){
            return 2
        }
        if (userPass.value.isNullOrEmpty()){
            return 3
        }
        return 200
    }
}