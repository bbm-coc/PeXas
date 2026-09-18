package com.pexas.accounting.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pexas.accounting.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AccountingViewModel(app: Application) : AndroidViewModel(app) {
    private val db = AppDatabase.get(app)

    val companies = db.companyDao().observeAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val parties = db.partyDao().observeAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val items = db.itemDao().observeAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val sales = db.saleDao().observeAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val purchases = db.purchaseDao().observeAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addCompany(name: String, fy: String) = viewModelScope.launch {
        if (name.isNotBlank()) db.companyDao().insert(Company(name = name.trim(), financialYear = fy))
    }
    fun addParty(name: String, phone: String, gstin: String, address: String) = viewModelScope.launch {
        if (name.isNotBlank()) db.partyDao().insert(Party(name = name.trim(), phone = phone, gstin = gstin, address = address))
    }
    fun addItem(name: String, unit: String, sale: Double, purchase: Double, gst: Double) = viewModelScope.launch {
        if (name.isNotBlank()) db.itemDao().insert(Item(name = name.trim(), unit = unit, saleRate = sale, purchaseRate = purchase, gstPercent = gst))
    }
    fun addSale(invoice: String, date: String, party: String, amount: Double) = viewModelScope.launch {
        if (invoice.isNotBlank()) db.saleDao().insert(Sale(invoiceNo = invoice, date = date, party = party, amount = amount))
    }
    fun addPurchase(bill: String, date: String, party: String, amount: Double) = viewModelScope.launch {
        if (bill.isNotBlank()) db.purchaseDao().insert(Purchase(billNo = bill, date = date, party = party, amount = amount))
    }
}
