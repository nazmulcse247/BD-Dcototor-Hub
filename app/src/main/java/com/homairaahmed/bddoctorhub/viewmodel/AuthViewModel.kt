package com.homairaahmed.bddoctorhub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel() : ViewModel() {

    val userName = MutableLiveData<String>()
    val userPass = MutableLiveData<String>()


    fun loginDataValidation(): Boolean {
        return !userName.value.isNullOrEmpty() && !userPass.value.isNullOrEmpty()
    }
}