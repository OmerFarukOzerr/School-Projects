package com.ofo.lkprojem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun olustur(view: View) { //burada string olanlara da null s.  denedim ama gerek yokmuş string boş olsada null olmuyor string oluyor
        var ad = wAd.text.toString()
        var ıs = wIs.text.toString()
        var cins = wCins.text.toString()
        var yas = wYas.text.toString().toIntOrNull() //ornul yapmadan hata verdi null olabileceğini söylemen lazım
        if (yas == null) {
            tSonuc.text = "geçerli bir yas değeri giriniz"
        } else {
            var superk = superKahraman(ad, ıs, cins, yas)
            tSonuc.text = "ad : ${superk.ad}, ıs: ${superk.ıs}, cins: ${superk.cins}, yas: ${superk.yas}"
        }

    }

}
//property ve constructor gösterimi Javada zorunlu kotlinde primary constructor tanımlayabiliyoruz
//ilk classta değişken tanımladık, sonra secondary constructorla yaptık, en son da primary de tanımladık













