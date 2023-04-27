package com.homairaahmed.bddoctorhub.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.homairaahmed.bddoctorhub.R

class ImageUtils {

    companion object{

        fun showNetworkImage(imageView: ImageView, context : Context, imageUrl : String){
            Glide.with(context).load(imageUrl).transition(DrawableTransitionOptions.withCrossFade(500)).placeholder(
                R.drawable.placeholder).into(imageView)
        }
    }
}