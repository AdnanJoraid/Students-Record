package com.example.project_joraid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var vm: MyViewModel
    val myPrefs = "PrefsFile"

    var keepMeLoggedIn: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)
        val mySharedPref = getSharedPreferences(myPrefs, MODE_PRIVATE)
        var isChecked = mySharedPref.getBoolean("rememberMe", false)
        if (isChecked){
            var permUsername = mySharedPref.getString("username", "")
            var permPassword = mySharedPref.getString("password", "")
            etUserName.setText(permUsername)
            etPassword.setText(permPassword)
            cbRememberMe.isChecked = true
       }



    }

     fun loginBtnEvent(view: View) {
        //all the login functionally will be inside this function
        var intentMainScreen = Intent(this, MainScreen::class.java)

        var username = etUserName.text.toString()
        var password = etPassword.text.toString()
        var rememberMe = cbRememberMe.isChecked.toString()
         val sharedPref = getSharedPreferences(myPrefs, MODE_PRIVATE)
         val editor = sharedPref.edit()
         var url = "https://mohameom.dev.fast.sheridanc.on.ca/login/verify.php?name=${username}&password=${password}"
         vm.makeRequest(url)

        if (username.isNotEmpty() && password.isNotEmpty()) {
            try {
                GlobalScope.launch {
                    delay(500)
                    if (vm.loginIsValid.value == "valid") {
                        intentMainScreen.putExtra("username", username)
                        if (rememberMe == "true"){
                            keepMeLoggedIn = true
                            editor.putString("username", username)
                            editor.putString("password", password)
                            editor.putBoolean("rememberMe", keepMeLoggedIn)
                            editor.apply()
                            startActivity(intentMainScreen)
                            finish()
                        } else if (rememberMe == "false"){
                            keepMeLoggedIn = false
                            editor.putBoolean("rememberMe", keepMeLoggedIn)
                            editor.apply()
                            intent.putExtra("name", username)
                            startActivity(intentMainScreen)
                            finish()
                        }
                    } else if (vm.loginIsValid.value == "invalid"){
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@MainActivity, "The account information you just entered doesn't exist. Please try again!", Toast.LENGTH_LONG).show()
                        }

                    }
                }
            } catch (e: Exception) {
                Log.d("my json error", e.toString())
            }
        }else if(username.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_LONG).show()
        }




    }
}