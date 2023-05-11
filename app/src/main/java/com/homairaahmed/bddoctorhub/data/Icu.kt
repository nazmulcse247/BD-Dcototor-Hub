package com.homairaahmed.bddoctorhub.data

data class Icu (
    var hospitalName : String,
    var location : String,
    var number : String,
    var latitude : String,
    var longitude : String
) {
    constructor() : this("", "", "","","")
}
