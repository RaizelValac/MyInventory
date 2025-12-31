package com.example.myinventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myinventory.ui.theme.MyInventoryTheme
import com.example.myinventory.ui.theme.screens.InventoryScreen
import com.example.myinventory.worker.BackupWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        scheduleDailySave()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyInventoryTheme {
                InventoryScreen()
                }
            }
        }
    fun scheduleDailySave() {
        val workRequest = PeriodicWorkRequestBuilder<BackupWorker>(1, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder(
                ).setRequiresStorageNotLow(true).build()
            )

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "DailyBackup",
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            workRequest.build()
        )
    }



}

