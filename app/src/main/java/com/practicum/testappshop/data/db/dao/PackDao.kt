package com.practicum.testappshop.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.practicum.testappshop.data.db.entity.BarcodeEntity
import com.practicum.testappshop.data.db.entity.FullPackEntity
import com.practicum.testappshop.data.db.entity.PackEntity
import com.practicum.testappshop.data.db.entity.PackPriceEntity
import com.practicum.testappshop.data.db.entity.UnitEntity

@Dao
interface PackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPack(pack: PackEntity): Long

    @Query("SELECT * FROM pack WHERE id = :id")
    suspend fun getPackById(id: Long): FullPackEntity?

    @Query("SELECT * FROM pack")
    suspend fun getAllPacks(): List<FullPackEntity>

    @Update
    suspend fun updatePack(pack: PackEntity)

    @Delete
    suspend fun deletePack(pack: PackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPackPrice(packPrice: PackPriceEntity): Long

    @Query("SELECT * FROM pack_price WHERE id = :id")
    suspend fun getPackPriceById(id: Long): PackPriceEntity?

    @Query("SELECT * FROM pack_price WHERE packId = :packId")
    suspend fun getPackPricesByPackId(packId: Long): List<PackPriceEntity>

    @Update
    suspend fun updatePackPrice(packPrice: PackPriceEntity)

    @Delete
    suspend fun deletePackPrice(packPrice: PackPriceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBarcode(barcode: BarcodeEntity): Long

    @Query("SELECT * FROM barcode WHERE id = :id")
    suspend fun getBarcodeById(id: Long): BarcodeEntity?

    @Query("SELECT * FROM barcode")
    suspend fun getAllBarcodes(): List<BarcodeEntity>

    @Delete
    suspend fun deleteBarcode(barcode: BarcodeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: UnitEntity): Long

    @Query("SELECT * FROM unit WHERE id = :id")
    suspend fun getUnitById(id: Long): UnitEntity?

    @Query("SELECT * FROM unit")
    suspend fun getAllUnits(): List<UnitEntity>

    @Update
    suspend fun updateUnit(unit: UnitEntity)

    @Delete
    suspend fun deleteUnit(unit: UnitEntity)
}