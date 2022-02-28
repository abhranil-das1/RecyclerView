package com.example.mvvm.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm.R
import com.example.mvvm.databinding.DataRowItemBinding
import com.example.mvvm.model.Data

class DataAdapter: RecyclerView.Adapter<DataViewHolder>(){
    private val dataList = mutableListOf<Data>()

    fun setDataList(list: List<Data>){
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataRowItemBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentData = dataList[position]

        holder.binding.firstName.text = currentData.first_name
        holder.binding.lastName.text = currentData.last_name
        holder.binding.email.text = currentData.email

        Glide.with(holder.binding.avatar)
            .load(currentData.avatar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.avatar)
    }

    override fun getItemCount() = dataList.size
}

class DataViewHolder(val binding: DataRowItemBinding): RecyclerView.ViewHolder(binding.root)