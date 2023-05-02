package com.homairaahmed.bddoctorhub.data

data class DoctorDetails(
    var about: String,
    var chamber: List<String>,
    var department: String,
    var education: String,
    var id: String,
    var image: String,
    var name: String,
    var phone: String,
    var professor: String,
    var specility: String,
    var status: String
) {
    constructor() : this("", listOf(), "", "", "", "", "", "", "", "", "")
}
