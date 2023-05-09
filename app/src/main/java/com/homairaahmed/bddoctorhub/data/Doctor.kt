package com.homairaahmed.bddoctorhub.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Doctor(
    var about : String,
    var chamber: List<String>,
    var department: String,
    var education: String,
    var id : String,
    var image: String,
    var name: String,
    var phone: String,
    var professor : String,
    var specility: String,
    var status : String,
    var hospitalCode : String,
    var location : String

) : Parcelable {
    constructor() : this("", listOf(), "", "", "", "", "", "", "", "", "","", "")

}
