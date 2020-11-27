package com.example.project_joraid
import android.content.Context
import androidx.room.*


@Database(entities = arrayOf(Record::class), version = 1)
abstract class MyDatabase: RoomDatabase() {

    abstract fun recordDao() : RecordDao

    companion object{
        private var instance: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase?{
            if (instance == null){
                synchronized(MyDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyDatabase::class.java,
                        "MyDB"
                    ).allowMainThreadQueries().build()
                }
            }

            return instance
        }

    }
}