package com.homairaahmed.bddoctorhub.data

data class Medical(
    val medicalId: Int,
    val medicalName: String,
    val medicalImage: String
) {
    constructor() : this(0, "", "")
}
