package com.example.project_joraid

import androidx.room.*

@Entity
data class Record (
    @PrimaryKey var id: String,
            @ColumnInfo(name = "student_score") var studentScore: String,
                    @ColumnInfo(name = "student_comments") var studentComments: String,
){

    override fun toString(): String {
        return "$id $studentScore $studentComments"
    }


}