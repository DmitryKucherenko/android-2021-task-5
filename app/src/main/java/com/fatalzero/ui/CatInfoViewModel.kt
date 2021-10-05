package com.fatalzero.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import com.bumptech.glide.RequestBuilder
import com.fatalzero.utils.Utils

class CatInfoViewModel : ViewModel() {
    var url: String? = null
    val downloadManager = Utils()

    fun downloadImage(url: String, context: Context) {
        downloadManager.downloadImage(url, context)
    }

    var glide: RequestBuilder<Drawable>? = null
}
