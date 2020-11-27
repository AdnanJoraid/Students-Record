package com.example.project_joraid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_modify_record.*

class ModifyRecord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_record)

        var id = intent.getSerializableExtra("id")  as String
        var score = intent.getSerializableExtra("score")  as String
        var comment = intent.getSerializableExtra("comment")  as String

        tvEditStudentId.text = "Student ID: $id"
        etUpdateScore.setText(score)
        etUpdateComment.setText(comment)

    }

    fun cancelBtn(view: View) {
        finish()
    }
    fun updateData(view: View) {}
    fun deleteData(view: View) {

    }
}