package com.practicum.testappshop.app

import android.content.Context
import android.util.Log
import com.practicum.testappshop.data.db.AppDatabase
import com.practicum.testappshop.data.db.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DatabaseInitializer(private val context: Context) {

    fun populateDatabase() {
        val db = AppDatabase.getInstance(context)

        val packDao = db.packDao()

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("aaa", "start filling")
            if (packDao.getAllPacks().isEmpty()) {
                Log.d("Will fill", "YES")
                val unitKilogram = UnitEntity(id = 1, name = "Кг")
                val unitLiter = UnitEntity(id = 2, name = "Л")
                val unitPiece = UnitEntity(id = 3, name = "Шт")

                packDao.insertUnit(unitKilogram)
                packDao.insertUnit(unitLiter)
                packDao.insertUnit(unitPiece)

                val packs = listOf(
                    PackEntity(
                        id = 0,
                        unitId = unitKilogram.id,
                        name = "Сахар",
                        type = 1,
                        quant = 5
                    ),
                    PackEntity(id = 1, unitId = unitLiter.id, name = "Молоко", type = 1, quant = 1),
                    PackEntity(id = 2, unitId = unitPiece.id, name = "Яйцо", type = 2, quant = 10),
                    PackEntity(
                        id = 3,
                        unitId = unitKilogram.id,
                        name = "Куриное филе",
                        type = 1,
                        quant = 1
                    ),
                    PackEntity(
                        id = 4,
                        unitId = unitLiter.id,
                        name = "Сок апельсиновый",
                        type = 1,
                        quant = 1
                    ),
                    PackEntity(id = 5, unitId = unitPiece.id, name = "Хлеб", type = 2, quant = 1),
                    PackEntity(id = 6, unitId = unitKilogram.id, name = "Рис", type = 1, quant = 1),
                    PackEntity(id = 7, unitId = unitLiter.id, name = "Вода", type = 1, quant = 1)
                )

                for (pack in packs) {
                    val packId = packDao.insertPack(pack)

                    val packPrice = PackPriceEntity(
                        id = 0,
                        packId = packId,
                        price = (Math.random() * 10000).toInt() + 50,
                        bonus = (Math.random() * 100).toInt()
                    )
                    db.packDao().insertPackPrice(packPrice)

                    val barcode = BarcodeEntity(
                        id = 0,
                        packId = packId,
                        body = "46000000000${(1..999).random()}"
                    )
                    db.packDao().insertBarcode(barcode)
                }
            }

            Log.d("Packs: ", packDao.getAllPacks().toString())
            Log.d("aaa", "end filling")
        }
    }

}