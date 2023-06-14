package com.ofo.kahramankitabdeneme

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    //intent alacağız görünümler oluşturulrken oluşacağı için buraya yazılır

        val intent = intent
        val ad = intent.getStringExtra("ad")
        textView.text = ad


        val bitmap = intent.getIntExtra("bitmap", 0)
        val gorsel = BitmapFactory.decodeResource(applicationContext.resources, bitmap)
        imageView.setImageBitmap(gorsel)
        

    }
}