package com.fullpagedeveloper.githubuserapp.extention

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fullpagedeveloper.githubuserapp.R

fun getProgresDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 20f
        start()
    }
}

fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable?) {
    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(url)
        .into(this)
}