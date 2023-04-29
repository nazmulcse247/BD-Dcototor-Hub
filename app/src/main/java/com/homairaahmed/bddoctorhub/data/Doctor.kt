package com.homairaahmed.bddoctorhub.data

data class Doctor(
    var name: String,
    var professor: String,
    var speciality: String,
    var degree: String,
    var rating: String,
    var image: Int
) {
    constructor() : this("", "", "", "", "",0)
}
