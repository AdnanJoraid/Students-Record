package com.example.project_joraid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_all_record.*

class AllRecord : AppCompatActivity(), MyAdapter.OnItemClickListener {
    var vm: MyViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_record)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(MyViewModel::class.java)
        var layoutManager = LinearLayoutManager(this)
        var data = setUpData()
        var adapter = MyAdapter(data, this, this)

        rvRecords.layoutManager = layoutManager
        rvRecords.adapter = adapter

    }


    private fun setUpData() : ArrayList<Record>{
        vm?.getRecords()
        val records = vm?.records?.value as ArrayList

        return records
    }



    fun mainPageBtn(view: View) {
        finish()
    }

    override fun onItemClick(position: Int) {
        val clickedItemId = setUpData()[position].id
        val clickedItemScore = setUpData()[position].studentScore
        val clickedItemComment = setUpData()[position].studentComments
        var modifyIntent = Intent(this, ModifyRecord::class.java)
        modifyIntent.putExtra("id", clickedItemId)
        modifyIntent.putExtra("score", clickedItemScore)
        modifyIntent.putExtra("comment", clickedItemComment)
        startActivity(modifyIntent)
    }
}