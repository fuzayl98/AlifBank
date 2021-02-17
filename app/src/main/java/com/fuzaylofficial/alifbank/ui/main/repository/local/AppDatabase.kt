package com.fuzaylofficial.alifbank.ui.main.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fuzaylofficial.alifbank.models.Guides.Guide
import com.fuzaylofficial.alifbank.ui.main.repository.local.GuidesDao

@Database(entities = [Guide::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun guidesDao(): GuidesDao
}