package com.ofo.kahramankitabdeneme

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class recyclerAdapter(val adListe : ArrayList<String>, val liste : ArrayList<Int>)
    : RecyclerView.Adapter<recyclerAdapter.VH>() {

    class VH(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        //tercihe bağlı adını koyduğumuz item view un oluşturulmsı, infilate edilierek layoutuna bağlanması
        //bu class ın amacını yerine getirmesi için burası, context alıyorken parenttan alınır, viewGroup sub-
        //class görünümlerin içiçe olabldiği özel bir View classı, recyclerView ın scroll ve ardaarda görünüm
        //öelliği var ikincisi buradan gelir
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)

        return VH(itemView)
    }

    override fun getItemCount(): Int {
        //bu ilk implement ediliyoren aşağıda sırası önemli aslında, kısaca position içi sayı

        return adListe.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        holder.itemView.row.text = adListe.get(position)
        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, MainActivity2::class.java)
            intent.putExtra("ad", adListe.get(position))
            intent.putExtra("bitmap", liste.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }
}