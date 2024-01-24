package com.example.diary.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R
import com.example.diary.data.models.DiaryEntity
import com.example.diary.data.models.Priority

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<DiaryEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.title_txt).text = dataList[position].date
        holder.itemView.findViewById<TextView>(R.id.description_txt).text =
            dataList[position].description

        when (dataList[position].priority) {
            Priority.HIGH -> holder.itemView.findViewById<Spinner>(R.id.priority_indicator)
                .setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.red)
                )
            Priority.MEDIUM -> holder.itemView.findViewById<Spinner>(R.id.priority_indicator)
                .setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.yellow)
                )
            Priority.LOW -> holder.itemView.findViewById<Spinner>(R.id.priority_indicator)
                .setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.context, R.color.green)
                )
        }
    }

    fun setData(diaryEntity: List<DiaryEntity>){
        this.dataList = diaryEntity
        notifyDataSetChanged()
    }
}