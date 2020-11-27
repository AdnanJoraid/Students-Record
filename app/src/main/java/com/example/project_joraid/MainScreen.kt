package com.example.project_joraid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreen : AppCompatActivity() {
    val myPrefs = "PrefsFile"
    var userAction = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        val mySharedPref = getSharedPreferences(myPrefs, MODE_PRIVATE)
        var username = mySharedPref.getString("username", "")
        var isChecked = mySharedPref.getBoolean("rememberMe", false)
        if (isChecked){
            tvWelcomeMsg.text = "Hello, $username"
        }else {
            var name = intent.getSerializableExtra("username") as String
            tvWelcomeMsg.text = "Hello, $name"
        }

    }

    fun userSelection(view: View) {
        when (view.id){
            R.id.rbNewRecord ->{
                userAction = "new"
            }
            R.id.rbPreviousRecord ->{
                userAction = "previous"
            }
            R.id.rbLogout ->{
                userAction = "logout"
            }
        }
    }

    fun submitBtnEvent(view: View) {
        when (userAction) {
            "new" -> {
                return
            }
            "previous" -> {
                return
            }
            "logout" -> {
                var i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }


}