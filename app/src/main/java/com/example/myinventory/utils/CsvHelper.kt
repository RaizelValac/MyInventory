package com.example.myinventory.utils

import android.content.Context
import android.os.Environment
import com.example.myinventory.data.Product
import java.io.File
import java.io.FileWriter


object CsvHelper {
    fun getDatabsetoCsv(context: Context, product: List<Product>): String? {


        val filename = "inventory_backup.csv"
        val downloadDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val file = File(downloadDir, filename)

        return try {
            val writer = FileWriter(file,false)

            writer.append("ID,Name,Price,Quantity,Category\n")

            for (p in product) {
                writer.append("${p.id},${p.name},${p.price},${p.quantity},${p.category}\n")
            }

            writer.flush()
            writer.close()

            file.absolutePath

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}