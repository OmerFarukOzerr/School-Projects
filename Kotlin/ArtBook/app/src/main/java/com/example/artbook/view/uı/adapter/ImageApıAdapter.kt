package com.example.artbook.view.uı.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import javax.inject.Inject

class ImageApıAdapter @Inject constructor(
    private val glide : RequestManager

) : RecyclerView.Adapter<ImageApıAdapter.ImageVH>() {
    class ImageVH(itemView : View) : RecyclerView.ViewHolder(itemView)
        private var listener : ((String) -> Unit ) ? = null


    private val differ = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, differ)


    var images : List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.fragment_image_row, parent,false)

        return ImageVH(view)
    }

    override fun getItemCount() = images.size


    override fun onBindViewHolder(holder: ImageVH, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.imageRow)
        val image = images[position]

        holder.itemView.apply {
            glide.load(image).into(imageView)
            setOnClickListener {
                listener?.let {
                    it(image)
                } } }

    }

    fun customOnClickListener(listener : (String) -> Unit) {
        this.listener = listener

    }
}