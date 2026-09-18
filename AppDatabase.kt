package com.pexas.accounting.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Company::class, Party::class, Item::class, Sale::class, Purchase::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
    abstract fun partyDao(): PartyDao
    abstract fun itemDao(): ItemDao
    abstract fun saleDao(): SaleDao
    abstract fun purchaseDao(): PurchaseDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun get(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "accounting_billing.db"
                ).build().also { INSTANCE = it }
            }
    }
}
