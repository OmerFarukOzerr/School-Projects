package com.ofo.kahramankitabdeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adlar
        val animeListe = ArrayList<String>( )
        animeListe.add("gintoki")
        animeListe.add("levi")
        animeListe.add("saiki")
        animeListe.add("childe")
        animeListe.add("luffy")

        //drawable veriler
        val gintokiD = R.drawable.gintoki
        val leviD = R.drawable.levi
        val saikiD = R.drawable.saiki
        val childeD = R.drawable.childe
        val luffyD = R.drawable.luffy

        //drawable adresleri integerdır
        var drawableListe = ArrayList<Int>()
        drawableListe.add(gintokiD)
        drawableListe.add(leviD)
        drawableListe.add(saikiD)
        drawableListe.add(childeD)
        drawableListe.add(luffyD)

        //recyclerın türü için layout maneger

        val layoutManager = LinearLayoutManager(this)
        recycler.layoutManager = layoutManager

        //adapter
        val adapter = recyclerAdapter(animeListe, drawableListe)
        recycler.adapter = adapter



    }

}