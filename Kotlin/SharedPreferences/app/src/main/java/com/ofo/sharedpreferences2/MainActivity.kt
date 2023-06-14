package com.ofo.sharedpreferences2

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var shared : SharedPreferences
    var verilenVeri : String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shared = this.getSharedPreferences("test",
            Context.MODE_PRIVATE )  //sp dosyası ilk açılışta xml şeklide tırnak içindeki adla oluşturulur ve kalıcı olarak kaydedlir

        verilenVeri = shared.getString("şeftali", "") //eğer şeftali anahtar kelimesi atanırsa sonrki açılış için değeri al  yoksa boş koy


        if(verilenVeri != null){
            textView.text = verilenVeri //sonraki açılışta oncreatte en son kaydedilen değeri göster
        }
    }

    fun kaydet(view : View) {

        var alınanVeri = editText.text.toString()
        var editor = shared.edit()

        if (alınanVeri == "") {
            Toast.makeText(this, "geçerli bir değer giriniz", Toast.LENGTH_SHORT).show()
        }else {
        editor.putString("şeftali", alınanVeri).apply()
        textView.text = "kullanıcı adı: ${alınanVeri}" //her basışta sp de kaydedilen değer en son yazılanla değişir ama dosya hiç kaybolmaz
        }
                                  //sonraki açılışta gösterilmek üzre kaydedilir b
    }

    fun sil (view: View) {
        var editor = shared.edit()
        var alınanVeri = editText.text.toString()

        if (alınanVeri != null) {
            textView.text = "kullanıcı adı:"
            editor.remove("şeftali").apply()
        }

    }
}
//mantık şu  en başta veriler er yerden ulaşılsın diye oncreatta tanımlanır
//onclick fonksiyonunda veriler anlık olarak değişkene atanır, değer kod okunma sırasına göre o an
//kaydet fonksiyonunun değişkeninde tutulur, her kaydete basışımızda sp de kaydedilen değer yenisiyle değiştirilir
//on creatte tekrar veri çekip textview a atama yapmamamızın sebebi sp de kaydedilen değerin ilk açılışta orda gösterilmesi
