package com.example.project_joraid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)



    }

     fun loginBtnEvent(view: View) {
        //all the login functionally will be inside this function
        var intentMainScreen = Intent(this, MainScreen::class.java)

        var username = etUserName.text.toString()
        var password = etPassword.text.toString()
        var rememberMe = cbRememberMe.isChecked.toString()
         var url = "https://mohameom.dev.fast.sheridanc.on.ca/login/verify.php?name=${username}&password=${password}"
         vm.makeRequest(url)

        if (((username.isNotEmpty()) && (password.isNotEmpty()))) {
            try {
                GlobalScope.launch {
                    delay(500)
                    if (vm.loginIsValid.value == "valid") {
                        intentMainScreen.putExtra("username", username)
                        if (rememberMe == "true"){
                            val sharedPref = getSharedPreferences(myPrefs, MODE_PRIVATE)
                            val editor = sharedPref.edit()
                            editor.putString(username, rememberMe)
                            editor.apply()
                            startActivity(intentMainScreen)
                            finish()
                        } else if (rememberMe == "false"){
                            startActivity(intentMainScreen)
                            finish()
                        }
                    }
                }


            } catch (e: Exception) {
                Log.d("my json error", e.toString())
            }
        }else if(username.isEmpty() && password.isEmpty()){
            Toast.makeText(this, "Please enter valid information", Toast.LENGTH_LONG).show()
        }




    }
}