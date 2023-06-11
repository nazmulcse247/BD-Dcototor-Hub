package com.homairaahmed.bddoctorhub.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HealthTips(
    val id: String,
    val image: String,
    val publised : String,
    val title: String,
    val content : String,
    val writer : String



) : Parcelable{
    constructor() : this("", "", "", "", "", "")
}
