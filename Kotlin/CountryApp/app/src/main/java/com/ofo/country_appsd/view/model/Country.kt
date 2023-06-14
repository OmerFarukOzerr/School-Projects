package com.ofo.country_appsd.view.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country(
    //buranın tamamı bir table, dataların herbiri column, her column unic idsi var
    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val countryUrl: String?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val countryName: String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital: String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage: String?

    ) {

    //bu her data set(tüm countrylerin dahil olduğu yularıdaki) için bir uuid oluşturur kendisi de
    // bir column gibi çalışır aslında, uuid ile tüm bir Countrynin tüm datasetini(özelliklerini yani) çekebiliriz

    //bu opsiyonel constructer içerisinde değil o yüzden idleri benim eklemem lazım aslında, bu mappingini yapar sadece
    //aouto generate kafa karıştırmasın yani
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0


}