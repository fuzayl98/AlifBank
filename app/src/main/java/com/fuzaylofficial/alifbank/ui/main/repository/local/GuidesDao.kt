package com.fuzaylofficial.alifbank.ui.main.repository.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fuzaylofficial.alifbank.models.Guides

@Dao
interface GuidesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(guides: List<Guides.Guide>?)

    @Query("SELECT url FROM Guide WHERE name LIKE :query")
    fun pagingSource(query:String) : PagingSource<Int, Guides.Guide>

    @Query("DELETE FROM Guide")
    suspend fun clearAll()
}