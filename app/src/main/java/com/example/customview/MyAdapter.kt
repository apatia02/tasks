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
        with(holder.itemLayoutBinding) {
            textView.text = currentItem
            expandButton.setOnClickListener {
                val rotation = if (holder.expanded) 0f else 180f
                holder.expanded = !holder.expanded
                expandButton.animate().apply {
                    duration = 500
                    rotation(rotation)
                    start()
                }
            }
            root.setOnClickListener {
                val scaleX = if (holder.scaled) 1f else 1.5f
                val scaleY = if (holder.scaled) 1f else 1.5f
                holder.scaled = !holder.scaled
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
    RecyclerView.ViewHolder(itemLayoutBinding.root) {
    var expanded: Boolean = false
    var scaled: Boolean = false
}