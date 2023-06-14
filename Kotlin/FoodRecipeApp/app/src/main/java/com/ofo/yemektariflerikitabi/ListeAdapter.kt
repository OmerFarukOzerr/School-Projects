package com.ofo.yemektariflerikitabi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class ListeAdapter(val tarifAdListe : ArrayList<String>, val tarifIdListe : ArrayList<Int>): RecyclerView.Adapter<ListeAdapter.ListeVH>() {
    class ListeVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recycler_row, parent,false)
        return ListeVH(itemView)
    }

    override fun getItemCount(): Int {
        return tarifAdListe.size
    }

    override fun onBindViewHolder(holder: ListeVH, position: Int) {
        holder.itemView.recyclerViewTextView.text = tarifAdListe.get(position)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, tarif_kaydet :: class.java)
            intent.putExtra("id", tarifIdListe.get(position))
            val action = tarif_listeDirections.actionTarifListeToTarifKaydet(true,tarifIdListe.get(position))
            Navigation.findNavController(it).navigate(action)
        }

    }
}