package com.bignerdranch.android.okhttp;

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.okhttp.R
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

import org.json.JSONArray
import org.json.JSONObject


private const val ENDPOINT = "http://10.0.2.2:3000"  // Im using json-server running on my localhost and emulator
private const val BOOKS_URI = "/books"
private const val TITLE = "title"


class MainActivity : AppCompatActivity() {
    private lateinit var textView:TextView
    var okHttpClient: okhttp3.OkHttpClient = okhttp3.OkHttpClient()
    var books2:MutableList<String>?=null
    private val typeJson = "application/json".toMediaType()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView =findViewById(R.id.textView)
        val books = mutableListOf<String>()
      //  addBook("Pushkin")
        getList("ssss")



    }

    fun getList(url:String){
        val request: Request = Request.Builder().url("http://10.0.2.2:3000/books").build()
        okHttpClient.newCall(request).enqueue(object: Callback {


            override fun onFailure(call: Call, e: java.io.IOException) {
                println("Request Failure.")
            }

            override fun onResponse(call: Call, response: Response) {
                println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
                val responseData  = response.body?.string()


                runOnUiThread{
                    try {


                        val books = mutableListOf<String>()
                        val json = JSONArray(responseData)
                        for (i in 0 until json.length()) {
                            val jsonBook = json.getJSONObject(i)
                            val title = jsonBook.getString(TITLE)
                            books.add(title)
                        }

                        Handler(Looper.getMainLooper()).post {
                            // println( books.reduce { acc, s -> "$acc\n$s" })
                            //books.forEach{println(it)}

                            textView.text=books.reduce { acc, s -> "$acc\n$s" }
                            //for(b in books)println(b)
                        }

                    } catch (e: org.json.JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        })
    }

    fun addBook(book:String) {
        runOnUiThread {
            try {
                //val json = "{\"id\":1000,\"title\":\"John\"}"

                val json = JSONObject().apply {
                    put(TITLE, book)

                }

                val request: Request = Request.Builder()
                    .url("http://10.0.2.2:3000/books")
                    .post(json.toString().toRequestBody( contentType = typeJson))
                    .build()

                val call: Call = okHttpClient.newCall(request)
                val response = call.execute()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}