package com.homairaahmed.bddoctorhub.data

data class Ambulance(
    val name: String,
    val location: String,
    val number: String,

) {
    constructor() : this("", "", "")
}
