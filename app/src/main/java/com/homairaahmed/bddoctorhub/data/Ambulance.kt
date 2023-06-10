package com.homairaahmed.bddoctorhub.data

data class Ambulance(
    val ambulanceId: String,
    val ambulanceName: String,
    val ambulanceNumber: String,

) {
    constructor() : this("", "", "")
}
