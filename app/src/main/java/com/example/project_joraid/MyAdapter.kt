package com.example.project_joraid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class MyAdapter(var data: ArrayList<Record>, var context: Context, private val listener: OnItemClickListener): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION){
                listener.onItemClick(pos)
            }
        }
    }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.row, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        holder.itemView.tvId.text = data[position].id
        holder.itemView.tvScore.text = data[position].studentScore
        holder.itemView.tvComment.text = data[position].studentComments
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

