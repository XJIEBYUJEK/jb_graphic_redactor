package com.example.graphic_redactor

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class CanvasView(context: Context): View(context) {


    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    var drawColor = ResourcesCompat.getColor(resources, R.color.black, null)
    var userWidth = 12f
    var figureFlag = "point"


    var paint = Paint().apply {
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = userWidth
    }

    fun clear() {
        extraCanvas.drawColor(backgroundColor)
        invalidate()
    }
    fun changeColor(color: Int){
        drawColor = color
    }
    fun changeWidth(width: Float){
        userWidth = width
    }
    fun changeFigure(figure: String){
        figureFlag = figure
    }




    private var path = Path()

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f



    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    private var currentX = 0f
    private var currentY = 0f

    private fun touchStart() {
        paint.apply {
            color = drawColor
            strokeWidth = userWidth
            if (drawColor == ResourcesCompat.getColor(resources, R.color.white, null))
                strokeWidth = 80f
        }
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    var left = 0f
    var right = 0f
    var bottom = 0f
    var top = 0f

    private fun touchMove() {
        val dx = abs(motionTouchEventX - currentX)
        val dy = abs(motionTouchEventY - currentY)
        val startX = currentX
        val startY = currentY
        left = min(startX, motionTouchEventX)
        top = max(startY, motionTouchEventY)
        right = max(startX, motionTouchEventX)
        bottom = min(startY, motionTouchEventY)
        if (figureFlag == "point"){
                if (dx >= touchTolerance || dy >= touchTolerance) {

                    path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
                    currentX = motionTouchEventX
                    currentY = motionTouchEventY
                    extraCanvas.drawPath(path, paint)
                    invalidate()
                }
        }
    }


    private fun touchUp() {
        when(figureFlag){
            "oval" -> {
                extraCanvas.drawOval(left, top, right, bottom, paint)
                invalidate()
            }
            "rect" -> {
                extraCanvas.drawRect(left, top, right, bottom, paint)
                invalidate()
            }
            "line" -> {
                extraCanvas.drawLine(left, top, right, bottom, paint)
                invalidate()
            }
        }
        path.reset()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }


}