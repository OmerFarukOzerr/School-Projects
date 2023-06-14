package com.ofo.country_appsd.view.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ofo.country_appsd.view.model.Country

@Dao
interface CountryDAO {

    //data access object interface

    @Insert
    //buradaki long country argümanı sayısı kadar long değeri döndürür, diğer taraftakiyle bağlamamız lazım ama
    suspend fun insertAll(vararg Countries : Country) : List<Long>

    @Query("SELECT * FROM Country")
    suspend fun gelAllCountries() : List<Country>

    @Query("SELECT * FROM Country WHERE uuid = :Uuid")
    suspend fun getCountry(Uuid : Int) : Country

    @Query("DELETE FROM Country")
    suspend fun deleteAllCountries()
}