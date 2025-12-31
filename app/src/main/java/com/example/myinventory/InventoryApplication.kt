package com.example.myinventory

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// @HiltAndroidApp: Triggers Hilt's code generation
@HiltAndroidApp
class InventoryApplication : Application()