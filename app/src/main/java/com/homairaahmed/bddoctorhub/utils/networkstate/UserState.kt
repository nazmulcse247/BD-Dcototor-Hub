package com.homairaahmed.bddoctorhub.utils.networkstate

import com.homairaahmed.bddoctorhub.data.User

data class UserState(
    val data: User? = null,
    val error: String = "",
    val isLoading: Boolean = false
)