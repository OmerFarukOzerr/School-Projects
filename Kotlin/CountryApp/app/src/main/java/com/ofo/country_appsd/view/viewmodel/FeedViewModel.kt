package com.ofo.country_appsd.view.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.ofo.country_appsd.view.model.Country
import com.ofo.country_appsd.view.service.CountryDataBase
import com.ofo.country_appsd.view.service.CountryService
import com.ofo.country_appsd.view.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(app : Application) : MotherViewModel(app) {

    private val disposable = CompositeDisposable()
    private val countryAPIservice = CountryService()
    private var customSP = CustomSharedPreferences(getApplication())
    private val pTime = 1000000000L


    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()


    fun refresh() {
        val refreshTime = customSP.getTime()
        println("System time : ${refreshTime}")
        println("Nanotime : ${System.nanoTime()}")

        if (refreshTime != null && (System.nanoTime() - refreshTime) < pTime * 600 && refreshTime != 0L) {

            getDataFromDB()

        } else {
            //10 dakikadan büyükse, veya default değer 0 is, uygulama ilk açıldığında yani apidan al
            getDataFromAPI()

        }
    }

    fun refreshSwipe() {
        //swipe refresh için kesin buradan çeksin
        getDataFromAPI()
    }

    private fun showCountries(countryList : List<Country>) {
        //önemli nokta görünümler api'dan veriliyordu observerlara şimdi direkt Roomdan verdik fark öenmli
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    //sadece burayı ilgilendirir o yüzden private, verileri duruma göre ya API'dan ya da SQL den çekeceğiz,
    //o yüzden ayrı bir fonksiyonda yazıldı, API veriler ilk çekileceği zaman sonrasında da güncellemek gerekiyorsa,
    //kullanılacak. Bunu daha sonra kontrol edeceğiz room yazılınca falan.
    private fun getDataFromAPI() {
        // veri çekliliyorken loading screen olacak, işlemler  disposable içinde olacak bu lifecycler bitince erviceleri
        //kesmemize yarıyor, diğer türlü arkada devam ederler
        countryLoading.value = true

        disposable.add(
            countryAPIservice.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {

                    override fun onSuccess(t: List<Country>) {
                        storeDataInRoom(t)

                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                })
        )
        Toast.makeText(getApplication(), "API'dan alındı", Toast.LENGTH_LONG).show()
    }


    private fun storeDataInRoom(countryList : List<Country>) {

        launch {
            val dao = CountryDataBase(getApplication()).getDao()
            dao.deleteAllCountries()
            //** bu şimdi veri girişi kadar listeye long ekledi, country sayısını aldık yani
            val listLong = dao.insertAll(*countryList.toTypedArray())

            //data tarafında uuid columnunu opsiyonel olarak oluşturmuştuk, şimdi oraya insert
            // edilen Country sayısını yollayacağız ki 'uuid' generate olsun ve maplesn rowlarla aslında

            var i = 0
            while(i< listLong.size) {
                countryList[i].uuid = listLong[i].toInt()
                i++
            }
        showCountries(countryList)
        }

        customSP.saveTime(System.nanoTime())

    }

    private fun getDataFromDB() {
        countryLoading.value = true
        launch {
            val daoCountryList = CountryDataBase(getApplication()).getDao().gelAllCountries()
            showCountries(daoCountryList)
        }
        Toast.makeText(getApplication(), "dataBase'den alındı", Toast.LENGTH_LONG).show()
    }

    override fun onCleared() {
        super.onCleared()
    disposable.clear()

    }

}