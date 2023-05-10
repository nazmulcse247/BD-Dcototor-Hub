package com.homairaahmed.bddoctorhub.data

data class Icu (
    var hospitalName : String,
    var location : String,
    var number : String,
) {
    constructor() : this("", "", "")
}
