package com.practicum.testappshop.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "pack")
data class PackEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val unitId: Long,
    val name: String,
    val type: Int,
    val quant: Int,
)

@Entity(
    tableName = "pack_price",
    foreignKeys = [
        ForeignKey(
            entity = PackEntity::class,
            parentColumns = ["id"],
            childColumns = ["packId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PackPriceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val packId: Long,
    val price: Int,
    val bonus: Int
)

@Entity(
    tableName = "barcode",
    foreignKeys = [
        ForeignKey(
            entity = PackEntity::class,
            parentColumns = ["id"],
            childColumns = ["packId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BarcodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val packId: Long,
    val body: String
)

@Entity(tableName = "unit")
data class UnitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String
)

data class FullPackEntity(
    @Embedded
    val packEntity: PackEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "packId"
    )
    val packPriceEntity: PackPriceEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "packId",
    )
    val barcodeEntity: BarcodeEntity,

    @Relation(
        parentColumn = "unitId",
        entityColumn = "id"
    )
    val unitEntity: UnitEntity?,
)