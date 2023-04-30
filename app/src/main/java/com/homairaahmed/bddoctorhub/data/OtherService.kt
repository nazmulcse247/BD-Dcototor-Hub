package com.homairaahmed.bddoctorhub.data

data class OtherService(
    var serviceName: String,
    var serviceImage: String,
    var serviceId: String
) {
    constructor() : this("", "","")
}

