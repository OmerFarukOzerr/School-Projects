package com.example.artbook.view.repository

import androidx.lifecycle.LiveData
import com.example.artbook.view.database.Dao
import com.example.artbook.view.database.Model
import com.example.artbook.view.model.ImageResponse
import com.example.artbook.view.service.RetrofitAPI
import com.example.artbook.view.util.Resource
import javax.inject.Inject

class ArtRepository
    @Inject constructor(

    private val dao : Dao,
    private val retrofit : RetrofitAPI

    ) : ArtRepoInterface {


    override suspend fun insertArt(art: Model) {
        dao.insertAll(art)

    }

    override suspend fun deleteArt(art: Model) {
        dao.delete(art)

    }

    override fun takeArt(): LiveData<List<Model>> {
        return dao.takeAll()

    }

    override suspend fun imageSearch(art: String): Resource<ImageResponse> {


        //repodan bu çekilirken image response burada enli boylu değerlendiriliğ view modela paslanıor
        //view compenentlarında nasıl çalışacapu daha sonra derlendirlior
       return try {
            val response = retrofit.imageSearch(art)

            if(response.isSuccessful) {
                response.body()?.let {

                    return@let Resource.success(it)
                } ?: Resource.error("cevap var ama imageResponse yok", null)

            } else {
                Resource.error("response yok", null)
            }

        } catch (e : Exception) {
            Resource.error("no data babba", null)

        }

    }

}