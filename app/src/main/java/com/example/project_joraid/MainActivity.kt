package com.example.project_joraid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var vm: MyViewModel
    val myPrefs = "PrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)
        var intent = Intent(this, MainScreen::class.java)


    }

    fun loginBtnEvent(view: View) {
        //all the login functionally will be inside this function

        var username = etUserName.text.toString()
        var password = etPassword.text.toString()
        var rememberMe = cbRememberMe.isChecked.toString()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            try {
                var url = "https://mohameom.dev.fast.sheridanc.on.ca/login/verify.php?name=${username}&password=${password}"
                vm.makeRequest(url)
                if (vm.loginIsValid.value == "valid") {
                    intent.putExtra("username", username)
                    if (rememberMe == "true"){
                        val sharedPref = getSharedPreferences(myPrefs, MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putString(username, rememberMe)
                        editor.apply()
                        startActivity(intent)
                    } else{
                        startActivity(intent)
                    }
                }

            } catch (e: Exception) {
                Log.d("my json error", e.toString())
            }
        }
        Toast.makeText(this, "Please enter valid information", Toast.LENGTH_LONG).show()


    }
}