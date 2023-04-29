package com.homairaahmed.bddoctorhub.data

data class Category(
    val catName: String,
    val catImage: String,
    val catId: String

) {
    constructor() : this("", "", "")
}
