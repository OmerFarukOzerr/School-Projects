package com.example.artbook.view.repository

import androidx.lifecycle.LiveData
import com.example.artbook.view.database.Model
import com.example.artbook.view.model.ImageResponse
import com.example.artbook.view.util.Resource


interface ArtRepoInterface {

    suspend fun insertArt(art : Model)

    suspend fun deleteArt(art : Model)

    fun takeArt() : LiveData<List<Model>>

    suspend fun imageSearch(art : String) : Resource<ImageResponse>

}