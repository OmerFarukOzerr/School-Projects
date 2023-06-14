package com.ofo.country_appsd.view.service

import com.ofo.country_appsd.view.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryService {

    // URL = https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    private val BASE_URL = "https://raw.githubusercontent.com/"
    //bu builder asıl apidan veri çekme işlemini yapacak clienti oluşturan yapı, requestleri country apida yazdık
    //sonra burada bağladık get yapınca gelen countryleri bu servise bağladık daha sonra bunu da Viewmodela bağlayacağız
    val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData () : Single<List<Country>> {

        return api.getCountries()
    }

}