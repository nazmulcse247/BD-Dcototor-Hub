package com.homairaahmed.bddoctorhub.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AuthViewModel() : ViewModel() {

    val userName = MutableLiveData<String>()
    val userPass = MutableLiveData<String>()


    fun loginDataValidation(): Int {
        if (userName.value.isNullOrEmpty()){
            return 1
        }
        if (userPass.value.isNullOrEmpty()){
            return 2
        }
        return 200
    }
}