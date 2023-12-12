package com.example.customview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.databinding.ItemLayoutBinding


class MyAdapter(private val data: List<String>) :
    ListAdapter<String, MyViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = data[position]
        var isExpanded = false
        var isScaled = false
        with(holder.itemLayoutBinding) {
            textView.text = currentItem
            expandButton.setOnClickListener {
                val rotation = if (isExpanded) 0f else 180f
                isExpanded = !isExpanded
                expandButton.animate().apply {
                    duration = 500
                    rotation(rotation)
                    start()
                }
            }
            root.setOnClickListener {
                val scaleX = if (isScaled) 1f else 1.5f
                val scaleY = if (isScaled) 1f else 1.5f
                isScaled = !isScaled
                root.animate().apply {
                    duration = 500
                    scaleX(scaleX)
                    scaleY(scaleY)
                    start()
                }
            }
        }
    }

}

class DiffUtilCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}

class MyViewHolder(val itemLayoutBinding: ItemLayoutBinding) :
    RecyclerView.ViewHolder(itemLayoutBinding.root)