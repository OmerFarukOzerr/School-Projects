package com.ofo.country_appsd.view.service

import com.ofo.country_appsd.view.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    // URL = https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE_URL = https://raw.githubusercontent.com/
    //EXT = atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries (): Single<List<Country>>

}