package com.example.artbook.view.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Model::class), version = 1)
abstract class DataBase : RoomDatabase(){

    abstract fun getDao() : Dao //bu daonun implementation ı değil




    }


