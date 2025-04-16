package com.practicum.testappshop.domain.models

import android.os.Parcelable
import com.practicum.testappshop.domain.models.Purchase
import kotlinx.parcelize.Parcelize

@Parcelize
data class Purchase(
    val datetime: String,
    val items: List<PurchaseItem>
) : Parcelable {
    constructor() : this("", listOf())
}

@Parcelize
data class PurchaseItem(
    val product: Product,
    val count: Int,
) : Parcelable {
    constructor() : this(Product(), 0)
}
