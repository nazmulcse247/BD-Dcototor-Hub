package com.homairaahmed.bddoctorhub.data

data class Doctor(
    var doctorName: String,
    var doctorProfessor: String,
    var doctorSpeciality: String,
    var doctorDegree: String,
    var doctorRating: String,
    var doctorImage: Int
) {
    constructor() : this("", "", "", "", "",0)
}
