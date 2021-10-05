package com.fatalzero.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.fatalzero.utils.Utils

class CatInfoViewModel : ViewModel() {
    var url: String? = null
    val downloadManager = Utils()

    fun downloadImage(url: String, context: Context) {
        downloadManager.downloadImage(url, context)
    }
}
