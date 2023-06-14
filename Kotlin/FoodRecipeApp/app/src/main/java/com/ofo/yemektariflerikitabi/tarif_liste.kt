package com.ofo.yemektariflerikitabi

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tarif_liste.*

class tarif_liste : Fragment() {
var tarifAdListe = ArrayList<String>()
var tarifListe = ArrayList<String>()
var idListe = ArrayList<Int>()
private lateinit var listeAdapter: ListeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View = inflater.inflate(R.layout.fragment_tarif_liste, container, false)
        return View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeAdapter = ListeAdapter(tarifAdListe, idListe)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = listeAdapter

        veriAl()


    }
    fun veriAl() {
        try {
            activity?.let {
                val dataBase = it.openOrCreateDatabase("Tarif", Activity.MODE_PRIVATE, null)
                val cursor = dataBase.rawQuery("SELECT * FROM tarif", null)

                val idColumnIndex = cursor.getColumnIndex("id")
                val tarifAdColumnIndex = cursor.getColumnIndex("tarifad")
                val tarifColumnIndex = cursor.getColumnIndex("tarif")

                tarifListe.clear()
                idListe.clear()
                tarifAdListe.clear()

                while (cursor.moveToNext()) {

                    tarifAdListe.add(cursor.getString(tarifAdColumnIndex))
                    tarifListe.add(cursor.getString(tarifColumnIndex))
                    idListe.add(cursor.getInt(idColumnIndex))

                   /* println(cursor.getInt(idColumnIndex))
                    println(cursor.getString(tarifAdColumnIndex))
                */
                }

                listeAdapter.notifyDataSetChanged()
                cursor.close()
            }
        } catch(e : Exception) {
            e.printStackTrace()
        }



    }


}