package com.homairaahmed.bddoctorhub.data

data class HealthTips(
    val id: String,
    val image: String,
    val publised : String,
    val title: String,
    val content : String



) {
    constructor() : this("", "", "", "", "")
}
