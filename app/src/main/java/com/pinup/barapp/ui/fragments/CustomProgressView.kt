package com.pinup.barapp.ui.fragments

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class CircleLoaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val itemCount = 12
    private var currentFrame = 0

    private val animationRunnable = object : Runnable {
        override fun run() {
            currentFrame = (currentFrame + 1) % itemCount
            invalidate()
            postDelayed(this, 100)
        }
    }

    init {
        post(animationRunnable)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks(animationRunnable)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = width / 3.2f
        val rectWidth = 30f
        val rectHeight = 100f


        for (i in 0 until itemCount) {
            val angleRad = Math.toRadians((i * 30).toDouble())
            val x = (centerX + radius * cos(angleRad)).toFloat()
            val y = (centerY + radius * sin(angleRad)).toFloat()

            canvas.save()
            canvas.translate(x, y)
            canvas.rotate(i * 30f + 90f) // << вот здесь поворот на 90° для "лежачих" палочек

            val alpha = when {
                i == currentFrame -> 255
                (i + 1) % itemCount == currentFrame -> 180
                (i + 2) % itemCount == currentFrame -> 100
                else -> 60
            }

            paint.color = if (i % 2 == 0) Color.RED else Color.rgb(0, 150, 0)
            paint.alpha = alpha

            canvas.drawRoundRect(
                RectF(-rectWidth / 2, -rectHeight / 2, rectWidth / 2, rectHeight / 2),
                rectWidth / 2,
                rectWidth / 2,
                paint
            )

            canvas.restore()
        }
    }
}
