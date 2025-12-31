package com.example.myinventory.viewmodel

import android.R.attr.path
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myinventory.data.Product
import com.example.myinventory.repo.InventoryRepository
import com.example.myinventory.utils.CsvHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel@Inject constructor(
    private val repository: InventoryRepository
): ViewModel() {

    val products: StateFlow<List<Product>> = repository.getAllProducts()
        .stateIn(
            initialValue = emptyList(),
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000)
        )

    val totalValue: StateFlow<Double> = repository.getAllProducts().map { list ->
        list.sumOf { it.price * it.quantity }
    }.stateIn(
        initialValue = 0.0,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000)
    )


    fun insert(name: String, price: String, quantity: String) {
        if (name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()) {
            val product = Product(
                name = name,
                price = price.toDoubleOrNull() ?: 0.0,
                quantity = quantity.toIntOrNull() ?: 0
            )
            viewModelScope.launch {
                repository.insert(product)
            }
        } else return
    }

    fun delete(product: Product) {
        viewModelScope.launch {
            repository.delete(product)
        }
    }

    fun exportData(context: Context) {
        viewModelScope.launch {
            val currentList = products.value

            if (currentList.isEmpty()) return@launch

            val path = withContext(Dispatchers.IO) {

                CsvHelper.getDatabsetoCsv(context, currentList)
            }
            println("CSV Saved at: $path")


        }
    }
}







