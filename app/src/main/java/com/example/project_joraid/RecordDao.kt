package com.example.project_joraid

import androidx.room.*

@Dao
interface RecordDao {
    @Insert
    fun insertAll(vararg record: Record)

    @Delete
    fun deleteRecord(record: Record)

    @Update
    fun updateRecord(record: Record)

    @Query("SELECT * FROM record")
    fun getAll(): List<Record>


}