package com.example.customview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myAdapter = MyAdapter(createList())
        val recyclerView: RecyclerView = findViewById(R.id.items_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
        myAdapter.submitList(createList())
    }

    private fun createList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 1..SIZE_LIST) {
            list.add("Item $i")
        }
        return list
    }
    companion object {
        const val SIZE_LIST = 40
    }
}