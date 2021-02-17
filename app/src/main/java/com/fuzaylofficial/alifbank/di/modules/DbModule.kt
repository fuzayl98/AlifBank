package com.fuzaylofficial.alifbank.di.modules

import android.app.Application
import androidx.room.Room
import com.fuzaylofficial.alifbank.ui.main.repository.local.AppDatabase
import com.fuzaylofficial.alifbank.ui.main.repository.local.GuidesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application,
                AppDatabase::class.java, "guides.db")
                .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): GuidesDao {
        return appDatabase.guidesDao()
    }
}