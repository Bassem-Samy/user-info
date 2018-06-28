package com.concept.user.util

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide


interface ImageLoader {
    fun loadImageUrl(imageView: ImageView, url: String?, @DrawableRes placeholderDrawableId: Int)
}

class GlideImageLoader : ImageLoader {
    override fun loadImageUrl(imageView: ImageView, url: String?, placeholderDrawableId: Int) {
        Glide.with(imageView.context)
                .load(url)
                .placeholder(placeholderDrawableId)
                .dontAnimate()
                .into(imageView)
    }
}