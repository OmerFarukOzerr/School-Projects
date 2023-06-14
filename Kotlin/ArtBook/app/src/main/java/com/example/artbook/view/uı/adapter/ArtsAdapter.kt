package com.example.artbook.view.uı.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.databinding.ArtRowBinding
import com.example.artbook.view.database.Model
import javax.inject.Inject

class ArtsAdapter @Inject constructor(
    val glide: RequestManager

    ) : RecyclerView.Adapter<ArtsAdapter.ArtVH>() {
    class ArtVH(var view: ArtRowBinding) : RecyclerView.ViewHolder(view.root)

    private lateinit var binding : ArtRowBinding


    private val diffUtil = object : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Model, newItem: Model): Boolean {
            return oldItem == newItem
        }
    }
    //listeler arası farklırı bulup asenkron bir şekilde değiştrir
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)


    var arts : List<Model>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtVH {
        val layoutInflater = LayoutInflater.from(parent.context)

         binding =  DataBindingUtil.inflate(layoutInflater, R.layout.art_row,parent, false)

        return ArtVH(binding)
    }

    override fun getItemCount() = arts.size


    override fun onBindViewHolder(holder: ArtVH, position: Int) {
        holder.view.art = arts[position].also {
            glide.load(it.imageUrl).into(holder.view.rowImageView)
        }
    }


}