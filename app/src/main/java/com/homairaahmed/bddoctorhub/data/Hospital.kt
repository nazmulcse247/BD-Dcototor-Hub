package com.homairaahmed.bddoctorhub.data

data class Hospital(
    var hospitalName: String,
    var hospitalId: String
) {
    constructor() : this("", "")
}

