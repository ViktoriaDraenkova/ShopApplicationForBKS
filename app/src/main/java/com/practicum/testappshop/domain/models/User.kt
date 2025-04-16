package com.practicum.testappshop.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String,
    var surname: String,
    var patronymic: String,
)  : Parcelable {
    constructor(): this("", "", "", "")
}