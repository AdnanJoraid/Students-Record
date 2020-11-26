package com.example.project_joraid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun loginBtnEvent(view: View) {
        //all the login functionally will be inside this function

        var username = etUserName.text.toString()
        var password = etPassword.text.toString()
        var rememberMe = cbRememberMe.isChecked.toString()

       Toast.makeText(this, rememberMe, Toast.LENGTH_LONG).show()

        /*
        1-
         */
    }
}