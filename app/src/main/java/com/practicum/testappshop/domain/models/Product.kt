package com.practicum.testappshop.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val title: String,
    val stock: Int,
    val price: Int,
    val sale: Int,
    val pricePer: Int,
    val unitName: String,
    val barcode: String,
) : Parcelable {
    constructor(): this(0, "", 0, 0, 0, 0, "", "")
}