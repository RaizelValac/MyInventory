package com.example.myinventory.repo

import com.example.myinventory.data.Product
import com.example.myinventory.data.ProductDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InventoryRepository@Inject constructor(
    private val dao: ProductDao
) {
    fun getAllProducts(): Flow<List<Product>> {
        return dao.getAllProducts()
    }

    suspend fun insert(product: Product) = dao.insert(product)

    suspend fun delete(product: Product) = dao.delete(product)
}