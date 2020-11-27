package com.example.project_joraid

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStream
import org.json.JSONObject
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.CoroutineContext
import kotlin.math.log

class MyViewModel(app : Application) : AndroidViewModel(app) {

    var loginIsValid = MutableLiveData<String>()

    init {
        loginIsValid.value = ""
    }
    suspend fun loadData(url: String): String{
        var ins: InputStream? = null
        var result = ""


        try{
            var url = URL(url)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            ins = connection.inputStream
            result = ins.bufferedReader().use(BufferedReader::readText)

        } catch (e: Exception){
            Log.d("Json error-> ", e.toString())
        }

        return result
    }

    fun makeRequest(url: String){
        CoroutineScope(Dispatchers.IO).launch {
            var result = loadData(url)

            withContext(Dispatchers.Main){
                var json = getJson(result)

                var isValidLogin = json.optString("login")

                this@MyViewModel.loginIsValid.value = isValidLogin
            }
        }
    }

    fun getJson(jsonStr: String): JSONObject{
        var json = JSONObject(jsonStr)
        return json
    }
}