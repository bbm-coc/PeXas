package com.pexas.accounting.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "companies")
data class Company(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val financialYear: String = "2026-27"
)

@Entity(tableName = "parties")
data class Party(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val phone: String = "",
    val gstin: String = "",
    val address: String = ""
)

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val unit: String = "Nos",
    val saleRate: Double = 0.0,
    val purchaseRate: Double = 0.0,
    val gstPercent: Double = 0.0
)

@Entity(tableName = "sales")
data class Sale(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val invoiceNo: String,
    val date: String,
    val party: String,
    val amount: Double,
    val status: String = "Saved"
)

@Entity(tableName = "purchases")
data class Purchase(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val billNo: String,
    val date: String,
    val party: String,
    val amount: Double,
    val status: String = "Saved"
)
