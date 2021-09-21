package com.bignerdranch.android.test


import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         findViewById<Button>(R.id.button).setOnClickListener{
           test()
         }

    }

    data class Video(
        val uri: Uri,
        val name: String,
        val duration: Int,
        val size: Int
    )

    override fun onStart() {
        super.onStart()

    }

fun test(){
    val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
    with (sharedPref.edit()) {
        putInt("TEST", 1000)
        apply()
    }
}

}

