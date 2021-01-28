package com.example.project_joraid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_modify_record.*

class ModifyRecord : AppCompatActivity() {
    var vm: MyViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_record)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)


        var id = intent.getSerializableExtra("id")  as String
        var score = intent.getSerializableExtra("score")  as String
        var comment = intent.getSerializableExtra("comment")  as String

        tvEditStudentId.text = "Student ID: $id"
        etUpdateScore.setText(score)
        etUpdateComment.setText(comment)

    }

    fun cancelBtn(view: View) {
        var i = Intent(this, AllRecord::class.java)
        startActivity(i)
    }
    fun updateData(view: View) {
        var id = intent.getSerializableExtra("id") as String
        var updatedScore = etUpdateScore.text.toString()
        var updatedComment = etUpdateComment.text.toString()

        if (inputCheck(updatedScore, updatedComment) && (updatedScore < "11" && updatedScore < "0")){
            val updatedRecord = Record(id, updatedScore, updatedComment)
            vm?.updateRecord(updatedRecord)
            var i = Intent(this, AllRecord::class.java)
            startActivity(i)

        }
        Toast.makeText(this, "Please enter valid information.\nHint: the score should be >= 0 and < 11", Toast.LENGTH_SHORT).show()

    }
    fun deleteData(view: View) {
        var id = intent.getSerializableExtra("id") as String
        var updatedScore = etUpdateScore.text.toString()
        var updatedComment = etUpdateComment.text.toString()

        if (inputCheck(updatedScore, updatedComment)){
            val deletedRecord = Record(id, updatedScore, updatedComment)
            vm?.deleteRecords(deletedRecord)
            var i = Intent(this, AllRecord::class.java)
            startActivity(i)

        }

    }

    private fun inputCheck(score: String, comment:String): Boolean{
        return !(TextUtils.isEmpty(score) && TextUtils.isEmpty(comment))
    }
}