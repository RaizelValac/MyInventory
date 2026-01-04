package com.example.myinventory.worker

import android.R.attr.path
import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.myinventory.data.ProductDao
import com.example.myinventory.repo.InventoryRepository
import com.example.myinventory.utils.CsvHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

@HiltWorker
class BackupWorker@AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    private val dao: ProductDao): CoroutineWorker(context, params) {
        override suspend fun doWork(): Result {
            return withContext(Dispatchers.IO) {
                try {
                    val products = dao.getAllProducts().first()

                    if(products.isNotEmpty()) {
                        val path = CsvHelper.getDatabsetoCsv(context, products)
                        println("✅ Auto-Backup Successful: $path")
                    }
                    Result.success()
                }
                catch (e: Exception) {
                    e.printStackTrace()
                    Result.retry()
                }

            }
        }



}
