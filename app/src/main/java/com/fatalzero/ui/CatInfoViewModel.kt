package com.fatalzero.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.fatalzero.R
import com.fatalzero.utils.Utils


class CatInfoViewModel : ViewModel(){
    var url: String? = null

val downloadManager = Utils()
    fun downloadImage(url: String, context: Context) {
        downloadManager.downloadImage(url, context)
    }

     var glide:
         RequestBuilder<Drawable>? = null



}
