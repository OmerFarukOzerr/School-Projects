package com.ofo.country_appsd.view.util

import  android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ofo.country_appsd.R

fun ImageView.DownloadFromUrl(url : String?, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeHolderProgressBar(context : Context) : CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 48f
        start()
    }

}
@BindingAdapter("android:bindWithDownloadUrl")
fun bindWithDownloadUrl(view : ImageView, url : String?) {
    view.DownloadFromUrl(url, placeHolderProgressBar(view.context))

}
