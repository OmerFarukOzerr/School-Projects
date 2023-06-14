package com.example.artbook.view.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.String

@Entity(tableName = "art")
data class Model(

    val artName : String?,
    val artistName : String?,
    val year : Int?,
    val imageUrl : String?,

    @PrimaryKey(autoGenerate = true)
    val id : Int? = null

)
