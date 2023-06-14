package com.ofo.hesapmakinas1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //deger1 : Double? = null gibi diğer değişkkenleri de tanımlarsam her seferinde var yapmama gerek kalmaz
    //kontrolu ifflerle yaptığımdan degerlere ünlem koyarak işi çözerim üşendim ama sonra tabi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun bTopla(view: View){
        var deger1 = wDeger1.text.toString().toDoubleOrNull()
        var deger2 = wDeger2.text.toString().toDoubleOrNull()

        if(deger1 == null || deger2 == null ) {
            sonuc.text = "sayı gir len"
        }else {
            sonuc.text = "${deger1 + deger2}"
        }
    }

    fun bCıkar(view: View){
        var deger1 = wDeger1.text.toString().toDoubleOrNull()
        var deger2 = wDeger2.text.toString().toDoubleOrNull()

        if(deger1 == null || deger2 == null ) {
            sonuc.text = "sayı gir len"
        }else {
            sonuc.text = "${deger1 - deger2}"
        }
    }

    fun bBol(view: View){
        var deger1 = wDeger1.text.toString().toDoubleOrNull()
        var deger2 = wDeger2.text.toString().toDoubleOrNull()
        val bozuk : Double = 0.0
        if(deger1 == null || deger2 == null ) {
            sonuc.text = "sayı gir len"
        }else if (deger2 == bozuk){
            sonuc.text = "sıfıra nasıl bölecen len "
        }else {
            sonuc.text = "${deger1 / deger2}"
        }
    }

    fun bCarp(view: View){
        var deger1 = wDeger1.text.toString().toDoubleOrNull()
        var deger2 = wDeger2.text.toString().toDoubleOrNull()
        if(deger1 == null || deger2 == null ) {
            sonuc.text = "sayı gir len"
        }else {
            sonuc.text = "${deger1 * deger2}"
        }

    }
}