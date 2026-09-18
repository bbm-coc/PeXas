package com.pexas.accounting.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CompanyDao {
    @Query("SELECT * FROM companies ORDER BY name") fun observeAll(): Flow<List<Company>>
    @Insert suspend fun insert(company: Company)
    @Update suspend fun update(company: Company)
    @Delete suspend fun delete(company: Company)
}

@Dao
interface PartyDao {
    @Query("SELECT * FROM parties ORDER BY name") fun observeAll(): Flow<List<Party>>
    @Query("SELECT * FROM parties WHERE name LIKE '%' || :q || '%' OR phone LIKE '%' || :q || '%' OR gstin LIKE '%' || :q || '%' ORDER BY name")
    fun search(q: String): Flow<List<Party>>
    @Insert suspend fun insert(party: Party)
    @Update suspend fun update(party: Party)
    @Delete suspend fun delete(party: Party)
}

@Dao
interface ItemDao {
    @Query("SELECT * FROM items ORDER BY name") fun observeAll(): Flow<List<Item>>
    @Query("SELECT * FROM items WHERE name LIKE '%' || :q || '%' ORDER BY name")
    fun search(q: String): Flow<List<Item>>
    @Insert suspend fun insert(item: Item)
    @Update suspend fun update(item: Item)
    @Delete suspend fun delete(item: Item)
}

@Dao
interface SaleDao {
    @Query("SELECT * FROM sales ORDER BY id DESC") fun observeAll(): Flow<List<Sale>>
    @Query("SELECT * FROM sales WHERE invoiceNo LIKE '%' || :q || '%' OR party LIKE '%' || :q || '%' ORDER BY id DESC")
    fun search(q: String): Flow<List<Sale>>
    @Insert suspend fun insert(sale: Sale)
}

@Dao
interface PurchaseDao {
    @Query("SELECT * FROM purchases ORDER BY id DESC") fun observeAll(): Flow<List<Purchase>>
    @Insert suspend fun insert(purchase: Purchase)
}
