package com.ofo.runnable_handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var runnable : Runnable = Runnable {  } //initialazation yapmassak başta kullanamayız o yüzden fonksiyon(?) atamalıız
// daha sonra değiştirilecek zaten
    var numara = 0
    var handler = Handler(Looper.myLooper()!!)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun basla(view: View) { // yukarıda tüm  kapsamlarda kullanablmek için boş tanımlamıştık kullanımı böyle ama

        //runnable arka planda çalışmasını sağlar sanırım(?)
        runnable = object : Runnable {
            override fun run() {
            numara = numara + 1
            textView.text = "sayaç: ${numara}"

            handler.postDelayed(runnable,1000) // bu satır runnable ı delay eder (?)
            }

        }
        handler.post(runnable) //bu satırda her geldiğinde runnable çalıştırır dögü gibi sanırım (?)
    }
    fun durdur(view: View) {
        handler.removeCallbacks(runnable)
        numara = 0
        textView.text = "sayaç ${numara}"

    }

    //thread
    //iplik paralel programlama vs.
    //aynı anda birden fazla farklı süreci yürütmek, execute etmek için kullanılr

    //misal internetten bir veri inririrken aynı anda o veriyle video izlmeye izin verebiir misal
    //internetten veri çekmek gii bir çok konuda bu sorun karşımıza çıkar
    //runable ve yan sınıfı handler bu konuda kullanılırlar


    //runable
    //kullanımı object ile olarak sınıf değil arayüzdür, soyut sınıf ve arayüzlerden bir obje oluşturulamaz
    //handler burada kullanılır runable asıl olay olsada onu kullanabilmek için class olan handler kullanılır



    fun deneme ( ) {
        var x = 5 //weidt
        var y = 12 //height
        var max = 300
        var oran = x.toDouble() / y.toDouble()

        if (oran > 1) {
            //yatay
            max = x
            var height = x / oran

        } else {
            //dikey
            max = y
            var weidth = y * oran
        }

    }
    
}