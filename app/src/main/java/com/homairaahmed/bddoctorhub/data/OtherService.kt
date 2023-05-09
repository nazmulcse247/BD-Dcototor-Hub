package com.homairaahmed.bddoctorhub.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtherService(
    var serviceName: String,
    var serviceImage: String,
    var serviceId: String
) : Parcelable {
    constructor() : this("", "","")
}

