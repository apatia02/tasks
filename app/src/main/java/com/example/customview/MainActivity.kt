package com.example.customview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var presenter: PresenterInterface
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
//        appComponent.inject(this)
        val myAdapter = MyAdapter(createList())
        val recyclerView: RecyclerView = findViewById(R.id.items_rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
        myAdapter.submitList(createList())
    }

    private fun createList(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 1..40) {
            list.add("Item $i")
        }
        return list
    }

}