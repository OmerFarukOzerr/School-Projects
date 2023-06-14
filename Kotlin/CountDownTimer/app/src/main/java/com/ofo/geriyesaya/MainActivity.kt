package com.ofo.geriyesaya

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() { //mesela main activity appCompati uygulayıp bazı özelliklerini kullanıyor
    override fun onCreate(savedInstanceState: Bundle?) { //bu iki noktayl gösterilir ve ikiye ayrılır
        super.onCreate(savedInstanceState)  //biri arayüzü uygulama diğeri de miras alma
        setContentView(R.layout.activity_main)
// appCompat bir sınıf olduğu için miras almaya giriyor, nesene de oluşturulabilir aslında

        //abtract sınıflarda ise yeni bir obje oluşturulamıyor, özeliklerinden faydalanmak için bu yapı kullanılır
        // object : CountDownTimer olarak kullanılması gerekiyor burada object bilinmeyen sınıf ama kullanımını tam anlamadım

        object : CountDownTimer(15000,1000) { //AppCompactActivity tam olarak bir sanal sınıf değil mesela miras alıyor
            override fun onTick(p0: Long) { //kendi araüz ve soyut sınıflarımızı yazmayı obje odaklı programlamada göreeğiz
                textView.text = "${p0 / 1000}"
                //bunlar olmadan çalışmaz ileride absract sınıflarla çalışırken bunlarla uğraşacağız
                println(p0 / 1000)
            }
            //on tick her saniye attığında ne yapayim?, p0 kalan saniyeyi gösteren değişken
            override fun onFinish() { //bitince ne yapayim?
                textView.text = "sayaç bitti"
            }

        }.start() //**bu unutulursa sayaç çalışmaz

    //Absract Class
    //soyut sınıf

    //soyut sınıflarda nesne oluşturmuyoruz sadece başka yerlerde oluşturarak kullanabiliyoruz
    //soyut bir sınıftan bir nesne oluşturamayız, hoca miras gibi kavramlardan bahsetti ama detaylar obje odaklı programlama da
    //obje okdalı programlamalarda soyut sınıflarla işlem yaacağız sanıırm

    //interfaces cannot have constructors



    }


}
