package com.example.project_joraid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_record.*

class NewRecord : AppCompatActivity() {
    lateinit var scorePlaceHolder: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_record)
        sbScore?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
                scorePlaceHolder = seek.progress.toString()
            }
        })
    }

    fun saveRecord(view: View) {
        var studentId = etStudentId.text.toString()
        var score = scorePlaceHolder
        var comments = etComments.text.toString()

        //create a connection to a database and add the above information to it (if the values are not empty)

    }
    fun goBack(view: View) {
        finish()
    }
}