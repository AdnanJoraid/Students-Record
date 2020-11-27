package com.example.project_joraid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_new_record.*
import java.lang.Exception

class NewRecord : AppCompatActivity() {
    lateinit var scorePlaceHolder: String
    var vm: MyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_record)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)

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
        try{
            var studentId = etStudentId.text.toString()
            var score = scorePlaceHolder
            var comments = etComments.text.toString()
            if (studentId.isNotEmpty() && comments.isNotEmpty()){
                var record = Record(studentId, score, comments)

                Thread{
                    vm?.addRecords(record)
                    runOnUiThread{
                        etStudentId.text.clear()
                        etComments.text.clear()
                    }
                }.start()

            }
            Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show()

        } catch (e: Exception){
            Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show()
        }


        //create a connection to a database and add the above information to it (if the values are not empty)



    }
    fun goBack(view: View) {
        finish()
    }
}