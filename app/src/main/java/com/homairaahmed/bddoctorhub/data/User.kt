package com.homairaahmed.bddoctorhub.data


data class User(
    val name: String = "",
    val image: String = "",
    val email: String = "",
    val active: Boolean = false,
    val address: String = ""
)