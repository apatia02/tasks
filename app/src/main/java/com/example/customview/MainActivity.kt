package com.example.customview

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var customShapeView: CustomShapeView
    private lateinit var textViewCount: TextView
    private var shapeCount = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customShapeView = findViewById(R.id.customShapeView)
        textViewCount = findViewById(R.id.textViewCount)
        customShapeView.colorArray = intArrayOf (Color.BLUE, Color.CYAN, Color.BLACK, Color.RED)
        customShapeView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                shapeCount++
                if (shapeCount == 10) shapeCount = 0
                textViewCount.text = shapeCount.toString()
            }
            false
        }
    }
}