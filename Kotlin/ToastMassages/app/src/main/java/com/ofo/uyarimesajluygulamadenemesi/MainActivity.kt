package com.ofo.uyarimesajluygulamadenemesi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Hoşgeldiniz.", Toast.LENGTH_SHORT).show()

    }

    fun olmassa(view: View) {

        var uyarıMesajı = AlertDialog.Builder(this)
        uyarıMesajı.setTitle("UYARI!")
        uyarıMesajı.setMessage("Farklı bir değer giriniz")
        uyarıMesajı.setPositiveButton("evet", DialogInterface.OnClickListener { dialogInterface, i ->
            Toast.makeText(this, "okey dene bakalım", Toast.LENGTH_SHORT).show()
        })

        uyarıMesajı.setNegativeButton("hayır",DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })
        uyarıMesajı.show()
}
}