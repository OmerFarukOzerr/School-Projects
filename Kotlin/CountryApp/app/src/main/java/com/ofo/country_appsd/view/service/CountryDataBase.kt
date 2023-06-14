package com.ofo.country_appsd.view.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ofo.country_appsd.view.model.Country

//normalde singlton yapmaya gerek yok ama cororutinelerle birlikte çalışsın diye öyle yapıyoruz
@Database(entities =  arrayOf(Country::class), version = 1)
abstract class CountryDataBase : RoomDatabase(){

    abstract fun getDao() : CountryDAO //abstact olduğu için argüman istemez, return type o implementation değil

    //singleton tek threatten luşılabilmesini yani; eş zamanlı olarak bu classtan tek instance oluşturulabilmesini
    //sağlayacak o yüzden singleton ile çalışmaz mantıklı olacaktır appimiz veriyi ayı anda farklı threadlerden
    //ulaşmak isterse conflict olacaktır bu yüzden burada senkron programming yapmalıyız
    //senkron programmingde processler birbirini beklerler


    companion object {

        @Volatile private var instance : CountryDataBase? = null

        private val lock = Any()
        //instance var mı yok mu kontrol eder yoksa önce senkronize eder, aşağıd instance oluşturacağız şimdi
        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: createDataBase(context).also {
                instance = it
            }
        }
        //database build edilir
        private fun createDataBase(context : Context): CountryDataBase = Room.databaseBuilder(
            context.applicationContext, CountryDataBase::class.java, "database"
        ).build()

        //fonksiyonu "CountryDataBase" döndürecek şeklde de yazabiliridm eşitleyince type inferred oluyor sanırım idk hoca böyle yaptı
        //fonskiyonalrla ilgili yapmam gereken araştırmalarla alakalı sanırım
    }







    //Singleton
    //abstract classes, Object declaration and epressionları araştırıdktan sonra Singleton da araştırdım,
    //önemli şeyler şunlar:

    /*     Only one instance: The singleton class has only one instance and this is done by providing an instance of the class, within the class.
    Globally accessible: The instance of the singleton class should be globally accessible so that each class can use it.
    Constructor not allowed: We can use the init method to initialize our member variables.

     */
    
}