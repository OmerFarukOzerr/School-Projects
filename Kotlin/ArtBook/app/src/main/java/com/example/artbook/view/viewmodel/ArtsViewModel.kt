package com.example.artbook.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbook.view.database.Model
import com.example.artbook.view.model.ImageResponse
import com.example.artbook.view.repository.ArtRepoInterface
import com.example.artbook.view.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtsViewModel
    @Inject constructor
    (private val repository: ArtRepoInterface) : ViewModel() {


    //--4tane livedata var--

    //**bu direkt livedata döndürüyor repoda
    //arts fragmentda observer içinde kullanıdı

    //bu datada kayıtlı artları çekmek için
    val artList = repository.takeArt()




    //seçilen görsellin kendisi responseda datealı özellikleri var
    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images


    //bu api fragmentdaki alcağımı imageın urlsini tutar bunu detailda da çağırırız

    //seçilen görselin urls
    private val selectedImage = MutableLiveData<String>()
    val imageUrl : LiveData<String>
        get() = selectedImage


    //bu a galiba testing için
    private var insertArtMessage = MutableLiveData<Resource<Model>>()
    val insertArtMsg : LiveData<Resource<Model>>
        get() = insertArtMessage


    fun resertArtMessage() {
        //notr testing
        insertArtMessage = MutableLiveData<Resource<Model>>()

    }

    fun setSelectedImageUrl(url :String) {
        selectedImage.postValue(url)

    }

    //swipe deletede kullanıldı
    fun deleteArt(art : Model) = viewModelScope.launch {
        repository.deleteArt(art)

    }

    fun insertArt(art : Model) = viewModelScope.launch {
        repository.insertArt(art)

    }


    fun makeArt(name : String, artistName :String, year : String) {
        if(name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            insertArtMessage.postValue(Resource.error("enter value", null))
            return
        }

        val yearInt = try {
            year.toInt()
        } catch (e : Exception) {
            insertArtMessage.postValue(Resource.error("sayı gir kelek", null))
            return

        }

        val art = Model(name, artistName, yearInt, selectedImage.value ?: "")
        insertArt(art)
        //kaydedip çıkarken selected imageı sıfırlıyor yoksa eski resim kalır probably
        selectedImage.value = ""
        insertArtMessage.postValue(Resource.success(art))

    }

    fun searchImage(art : String) {
        if(art.isEmpty()) {

            return
        }

        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.imageSearch(art)
            images.value = response
        }

    }

}

