package com.example.customview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlin.random.Random

class CustomShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val shapes = mutableListOf<Shape>()

    private val defaultColor: Int = attrs?.getAttributeIntValue(
        "http://schemas.android.com/apk/res-auto",
        "defaultColor",
        Color.GREEN
    ) ?: Color.GREEN
    private val colorsArrayId = attrs?.getAttributeResourceValue(
        "http://schemas.android.com/apk/res-auto",
        "colorsArray",
        0
    )

    var colorArray: IntArray? = null

    init {
        paint.color = defaultColor
        paint.style = Paint.Style.FILL

        if (colorsArrayId != null && colorsArrayId != 0) {
            colorArray = resources.getIntArray(colorsArrayId)
        }
    }

    //logic
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapes.forEach { shape ->
            paint.color = shape.color
            when (shape.shapeType) {
                ShapeType.CIRCLE -> canvas.drawCircle(shape.x, shape.y, shape.size.toFloat(), paint)
                ShapeType.SQUARE -> canvas.drawRect(
                    shape.x - shape.size,
                    shape.y - shape.size,
                    shape.x + shape.size,
                    shape.y + shape.size,
                    paint
                )

                ShapeType.ROUNDED_SQUARE -> canvas.drawRoundRect(
                    shape.x - shape.size,
                    shape.y - shape.size,
                    shape.x + shape.size,
                    shape.y + shape.size,
                    20f,
                    20f,
                    paint
                )
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            shapes.add(
                Shape(
                    event.x,
                    event.y,
                    getRandomColor(),
                    getRandomShape(),
                    Random.nextInt(20, 50)
                )
            )

            if (shapes.size >= 10) {
                shapes.clear()
                Toast.makeText(context, "Game over", Toast.LENGTH_SHORT).show()
            }
            invalidate()

        }
        return true
    }

    private fun getRandomColor(): Int {
        return colorArray?.let {
            if (it.isNotEmpty()) {
                it.random()
            } else {
                defaultColor
            }
        } ?: defaultColor
    }

    private fun getRandomShape(): ShapeType {
        val shapes = arrayOf(ShapeType.CIRCLE, ShapeType.SQUARE, ShapeType.ROUNDED_SQUARE)
        return shapes.random()
    }
}

enum class ShapeType {
    CIRCLE, SQUARE, ROUNDED_SQUARE
}

data class Shape(
    val x: Float,
    val y: Float,
    val color: Int,
    val shapeType: ShapeType,
    val size: Int
)