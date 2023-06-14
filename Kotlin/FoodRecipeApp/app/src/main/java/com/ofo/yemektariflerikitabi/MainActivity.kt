package com.ofo.yemektariflerikitabi

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //optios menu nun kendi onCreati diyebilriz menuyu buraya bağlarız
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.tarif_ekleme, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //options menu den bir şey seçilirse ne yapayim?
        //burada birden fazla item eklenebilir bir skıntı olmaması için if kullanırız
        if(item.itemId == R.id.tarif_ekleme) {
            val action = tarif_listeDirections.actionTarifListeToTarifKaydet()
            Navigation.findNavController(this, R.id.fragment ).navigate(action)
            //fragmentler arası geçiş yapıyoruz halen host ekranında başlangıçta liste fragmentı var mnü butonu diğerine geçmeye yarar yani

        }
        return super.onOptionsItemSelected(item)
    }

}