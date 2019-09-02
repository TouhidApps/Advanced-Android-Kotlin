package com.touhidapps.retrofitwithkotlin.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        url?.let {
            Glide.with(view.context).load(url).into(view)
        }
    }


}


