package com.touhidapps.retrofitwithkotlin.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }

}