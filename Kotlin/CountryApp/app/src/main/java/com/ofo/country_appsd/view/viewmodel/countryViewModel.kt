package com.ofo.country_appsd.view.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ofo.country_appsd.view.model.Country
import com.ofo.country_appsd.view.service.CountryDataBase
import kotlinx.coroutines.launch

class countryViewModel(app : Application ) : MotherViewModel(app) {
    val countries = MutableLiveData<Country>()



    fun refresh(Uuid : Int){
        launch {

            val country = CountryDataBase(getApplication()).getDao().getCountry(Uuid)
            countries.value = country

        }

    }
}