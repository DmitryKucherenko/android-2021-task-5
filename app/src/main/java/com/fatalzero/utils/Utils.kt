package com.fatalzero.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast

private const val DELIMITER = "/"
private const val MESSAGE="was downloaded to"

class Utils {
    fun downloadImage(url: String, context: Context, filePath: String? = Environment.DIRECTORY_PICTURES) {
        val filename = url.substringAfterLast(DELIMITER)
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(filename)
            .setDestinationInExternalPublicDir(filePath, filename)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        (context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager)
            .enqueue(request)

        Toast.makeText(
            context,
            "$filename $MESSAGE $filePath",
            Toast.LENGTH_LONG
        ).show()
    }
}
