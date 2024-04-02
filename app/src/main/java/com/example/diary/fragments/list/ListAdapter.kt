package com.example.diary.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.diary.R
import com.example.diary.data.models.DiaryEntity

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
        holder.itemView.findViewById<TextView>(R.id.date_txt).text = dataList[position].date
        holder.itemView.findViewById<TextView>(R.id.description_txt).text =
            dataList[position].description

        //переход на страницу обновления по клику. Мб лучше оберуть в cardView
        holder.itemView.findViewById<ConstraintLayout>(R.id.row_background).setOnClickListener {
            val action =
                ListFragmentDirections.actionListFragmentToUpdateFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(diaryEntity: List<DiaryEntity>) {
        this.dataList = diaryEntity
        notifyDataSetChanged()
    }
}