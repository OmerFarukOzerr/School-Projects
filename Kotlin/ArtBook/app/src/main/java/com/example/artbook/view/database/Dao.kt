package com.example.artbook.view.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@androidx.room.Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(art : Model)

    @Delete
    suspend fun delete(art : Model)

    @Query("SELECT * FROM art")
    fun takeAll () : LiveData<List<Model>>

}